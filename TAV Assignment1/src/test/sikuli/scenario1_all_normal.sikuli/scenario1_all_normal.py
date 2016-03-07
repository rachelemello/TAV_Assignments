from sikuli import *

import helper_linux

def run():
    print "Scenario 1 test\n"
    helper_linux.startGUI()
    helper_linux.startT()
    helper_linux.clearVals()
    helper_linux.enterVals(1,1,1)
    click(Pattern("1457360130758.png").similar(0.90))
    try:
        find(Pattern("1457360936236.png").similar(0.90))
        find(Pattern("1457362105077.png").similar(0.90))
        
    except:
        print "Part 1 of scenario 1 failed!"
        return
    print "Part 1 of 3 passed"

    helper_linux.clearVals()
    helper_linux.enterVals(50,125,225)
    click(Pattern("1457360130758.png").similar(0.90))
    try:
        find(Pattern("1457361089238.png").similar(0.90))
        find(Pattern("1457362105077.png").similar(0.90))
        
    except:
        print "Part 2 of scenario 1 failed!"
        return
    print "Part 2 of 3 passed"

    helper_linux.clearVals()
    helper_linux.enterVals(255,255,255)
    click(Pattern("1457360130758.png").similar(0.90))
    try:
        find(Pattern("1457361171116.png").similar(0.90))
        find(Pattern("1457362105077.png").similar(0.90))
    except:
        print "Part 3 of scenario 1 failed!"
        return
    print "Part 3 of 3 passed"
run()

    