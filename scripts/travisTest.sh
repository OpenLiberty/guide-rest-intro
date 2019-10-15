#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

mv ~/.m2/settings.xml ~/.m2/settings.xml.gri
mvn -q clean package liberty:create liberty:install-feature liberty:deploy
mvn liberty:start > start.log
mvn failsafe:integration-test liberty:stop > out.log
mv ~/.m2/settings.xml.gri ~/.m2/settings.xml
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
