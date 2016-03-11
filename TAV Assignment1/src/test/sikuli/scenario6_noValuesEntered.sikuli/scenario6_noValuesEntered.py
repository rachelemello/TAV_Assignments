from sikuli import *

    
def run():

    
    print "Scenario 6 test\n"
   
    click("1457704596494.png")
   
    click("1457703157649.png")
    try:
        find("1457703197548.png")
    except:
        print "Test for scenario 6 failed!"
        return
    print "Test for scenario 6 succeeded!"

run()
        
        

