from sikuli import *

import helper
reload(helper)

def run():
    print "Scenario 4 test\n"
    helper.startGUI()
    click(Pattern("1457099109175.png").similar(0.90))
    try:
        find(Pattern("1457099142653.png").similar(0.90))
    except:
        print "Test for scenario 4 failed!"
        return
    print "Test for scenario 4 succeeded!"

run()

        
    