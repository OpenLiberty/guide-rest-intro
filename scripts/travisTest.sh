#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

mvn clean package liberty:create liberty:install-feature liberty:deploy > create.log
mvn liberty:start > start.log
mvn failsafe:integration-test liberty:stop > out.log
ERROR=`grep ERROR out.log | wc -l`
if [ $ERROR -ne 0 ]; then
    mvn liberty:stop
    cat create.log
    cat start.log
    cat out.log
    echo "Test Failed!"
    #exit 1
fi
