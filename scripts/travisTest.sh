#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

mvn -q clean package liberty:create liberty:install-feature liberty:deploy
mvn -q liberty:start
mvn failsafe:integration-test liberty:stop > out.log
ERROR=`grep ERROR out.log | wc -l`
if [ $ERROR -ne 0 ]; then
    mvn liberty:stop
    cat out.log
    echo "Test Failed!"
    #exit 1
fi
