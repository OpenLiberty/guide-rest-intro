#!/bin/bash
#set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

mvn -q clean package liberty:create liberty:install-feature liberty:deploy
mvn liberty:start > start.log
mvn failsafe:integration-test liberty:stop > out.log
ERROR=`grep ERROR out.log | wc -l | awk '{$1=$1};1'`
if [ $ERROR -ne 0 ]; then
    mvn liberty:stop
    cat start.log
    cat target/liberty/wlp/usr/servers/defaultServer/logs/messages.log 
    cat out.log
    echo "Test Failed!"
    exit 1
fi
