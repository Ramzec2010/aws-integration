package com.roman.awsintegration;

import io.sphere.sdk.client.BlockingSphereClient;
import io.sphere.sdk.client.SphereRequest;
import io.sphere.sdk.queries.CollectionQueryModel;
import io.sphere.sdk.queries.QueryPredicate;
import io.sphere.sdk.subscriptions.Subscription;
import io.sphere.sdk.subscriptions.commands.SubscriptionDeleteCommand;
import io.sphere.sdk.subscriptions.queries.SubscriptionQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.sphere.sdk.queries.QueryExecutionUtils.queryAll;

@SpringBootApplication
public class AwsIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsIntegrationApplication.class, args);
	}


	List<String> keys = List.of("key1", "key2", "key3");
	List<String> notIncludedKeys = List.of("key1");

	BlockingSphereClient clientProvider;

	public void cleanSubscriptions() {
		List<Subscription> subscriptionList = queryAll(clientProvider, subscriptionQuery()).toCompletableFuture().join();
		List<String> deletedSubscriptions = subscriptionList.stream()
				.map(SubscriptionDeleteCommand::of)
				.map(this::executeBlocking)
				.map(Subscription::getKey)
				.collect(Collectors.toList());
		System.out.println(deletedSubscriptions);
	}


	private SubscriptionQuery subscriptionQuery() {
		return CollectionUtils.isEmpty(keys)
				? SubscriptionQuery.of()
				: SubscriptionQuery.of().withPredicates(QueryPredicate.of("some query string"));
	}

	private <T> T executeBlocking(SphereRequest<T> request) {
		return clientProvider.executeBlocking(request);
	}
}
