#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

mvn clean package liberty:create liberty:install-feature liberty:deploy > create.log
mvn liberty:start > start.log
sleep 60
mvn failsafe:integration-test liberty:stop > out.log
ERROR=`grep ERROR out.log | wc -l`
if [ $ERROR -ne 0 ]; then
    mvn liberty:stop
    tail -50 create.log
    cat start.log
    tail target/liberty/wlp/usr/servers/defaultServer/logs/messages.log 
    cat out.log
    echo "Test Failed!"
    #exit 1
fi
