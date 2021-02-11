amazon-linux-extras install java-openjdk11
yum install git
amazon-linux-extras install docker
service docker start
usermod -a -G docker ec2-user
chkconfig docker on
curl -L https://github.com/docker/compose/releases/download/1.28.2/docker-compose-Linux-x86_64 -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
git clone https://github.com/MarynaSydorenko/training.git
cd training
./build-local.sh