from sikuli import *

import helper
reload(helper)
def run():
    helper.startGUI()
    helper.startT()
    helper.enterVals('a',2,3)
    click(Pattern("1457012090425.png").similar(0.90))
    try:
        find(Pattern("1457012249180.png").similar(0.90))
    except:
        print "Test for scenario 2 failed!"
        return
    print "Test for scenario 2 succeeded!"

run()
        
        

