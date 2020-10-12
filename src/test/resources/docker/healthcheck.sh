#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE

echo "Checking if hub is ready"

while [ "$( curl -s http://172.20.110.97:4444/wd/hub/status | jq -r .value.ready )" != "true" ]
do
	sleep 1
done

# start the java command
java -cp qedgefinal.jar:qedgefinal-tests.jar:libs/* \
	-Dcucumber.options="classpath:features"	\
	org.testng.TestNG $EXECUTIONTYPE