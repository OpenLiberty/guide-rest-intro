#!/bin/bash
set -euxo pipefail

##############################################################################
##
##  Travis CI test script
##
##############################################################################

mvn -q clean package liberty:create liberty:install-feature liberty:deploy
mvn liberty:start
mvn failsafe:integration-test liberty:stop
mvn failsafe:verify
