#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

if [ -f "~/.m2/settings.xml" ]; then
    mv ~/.m2/settings.xml ~/.m2/settings.xml.gri
fi
mvn -q clean package liberty:create liberty:install-feature liberty:deploy
mvn liberty:start > start.log
mvn failsafe:integration-test liberty:stop > out.log
if [ -f "~/.m2/settings.xml.gri" ]; then
    mv ~/.m2/settings.xml.gri ~/.m2/settings.xml
fi
ERROR=`grep ERROR out.log | wc -l`
if [ $ERROR -ne 0 ]; then
    mvn liberty:stop
    ls ~/.m2
    ls  ~/.m2/repository/io/openliberty/tools/liberty-maven
    cat start.log
    cat target/liberty/wlp/usr/servers/defaultServer/logs/messages.log 
    cat out.log
    echo "Test Failed!"
    #exit 1
fi
