#!/bin/sh
####################################
#
# Starting node
#
####################################

java -Dwebdriver.chrome.driver="chromedriver" -jar selenium-server-standalone-3.6.0.jar -role node -hub http://localhost:4441/grid/register -browser "browserName=chrome, maxInstances=1" -port 5555

