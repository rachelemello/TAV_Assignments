from sikuli import *

import helper
reload(helper)

def run():
    print "Scenario 5 test\n"
    helper.startGUI()
    helper.startT()
    click(Pattern("1457538156838.png").similar(0.90).targetOffset(-6,-2))
    try:
        find(Pattern("1457538190816.png").similar(0.90))
    except:
        print "Test for scenario 5 failed!"
        return
    print "Test for scenario 5 succeeded!"
    return
run()

    
