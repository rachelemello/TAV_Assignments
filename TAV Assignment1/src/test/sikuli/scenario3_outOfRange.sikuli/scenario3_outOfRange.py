from sikuli import *

import helper
reload(helper)

def run():
    print "Scenario 3 test\n"

    #test 3.1
    helper.startGUI()
    helper.startT()
    helper.enterVals(300,2,3)
    click(Pattern("1457012090425.png").similar(0.90))
    wait(2) #it takes a bit before the error is detected
    try:
        find(Pattern("1457087016077.png").similar(0.90))
    except:
        print "Test for scenario 3.1 failed!\n"
        print "Test for scenario 3 aborted"
        return
    print "Test for scenario 3.1 succeeded!"
    helper.quitGUI()
    
    #test 3.2
    helper.startGUI()
    helper.startT()
    helper.enterVals(1,2,-5)
    click(Pattern("1457012090425.png").similar(0.90))
    wait(2)
    try:
        find(Pattern("1457087185565.png").similar(0.90))
    except:
        print "Test for scenario 3.2 failed!"
        return
    print "Test for scenario 3.2 succeeded!"
    return    

run()