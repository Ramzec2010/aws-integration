package com.roman.awsintegration;

import io.sphere.sdk.client.BlockingSphereClient;
import io.sphere.sdk.queries.QueryExecutionUtils;
import io.sphere.sdk.queries.QueryPredicate;
import io.sphere.sdk.subscriptions.Subscription;
import io.sphere.sdk.subscriptions.commands.SubscriptionDeleteCommand;
import io.sphere.sdk.subscriptions.queries.SubscriptionQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(QueryExecutionUtils.class)
public class AwsIntegrationApplicationTest {
    @Mock
    BlockingSphereClient client;
    @Mock
    CompletableFuture<List<Subscription>> completableFuture;
    @Mock
    CompletionStage<List<Subscription>> completionStage;
    @InjectMocks
    AwsIntegrationApplication application;


    @Test
    public void cleanSubscriptionsWhenEmptySubscriptionList() {
        List<Subscription> subscriptions = new ArrayList<>();
        SubscriptionQuery subscriptionQuery = SubscriptionQuery.of();

        PowerMockito.mockStatic(QueryExecutionUtils.class);
        BDDMockito.given(QueryExecutionUtils.queryAll(client, subscriptionQuery)).willReturn(completionStage);
        when(completableFuture.join()).thenReturn(subscriptions);
        when(completionStage.toCompletableFuture()).thenReturn(completableFuture);

        application.cleanSubscriptions();

        verify(client, never()).execute(any(SubscriptionDeleteCommand.class));
    }

    @Test
    public void cleanSubscriptionsWhenNotEmptySubscriptionList() {
        Subscription subscription = mock(Subscription.class);
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(subscription);
        SubscriptionQuery subscriptionQuery = SubscriptionQuery.of().withPredicates(QueryPredicate.of("some query string"));

        PowerMockito.mockStatic(QueryExecutionUtils.class);
        BDDMockito.given(QueryExecutionUtils.queryAll(client, subscriptionQuery)).willReturn(completionStage);
        when(completableFuture.join()).thenReturn(subscriptions);
        when(completionStage.toCompletableFuture()).thenReturn(completableFuture);

        Subscription deletedSubscription = mock(Subscription.class);
        when(deletedSubscription.getKey()).thenReturn("deletedKey");

        when(client.executeBlocking(SubscriptionDeleteCommand.of(subscription))).thenReturn(deletedSubscription);

        application.cleanSubscriptions();

        verify(client, atLeastOnce()).executeBlocking(SubscriptionDeleteCommand.of(subscription));
        verify(deletedSubscription, atMostOnce()).getKey();
    }

}