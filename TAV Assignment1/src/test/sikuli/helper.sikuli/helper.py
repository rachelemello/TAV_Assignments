from sikuli import *

'''
   Methods to start and quit the GUI, insert values, and start threads, 
   to be used in the test cases.
'''

def startGUI():
   ''' 
   Method to start the GUI via terminal.
   This method only works on Os.
   Change the variable "path" to match the location of your java classes.
   '''
    # directory where the java class files of the Matrix application are
   path ="Documents/Studies/VT16/TAV/TAV_Assignments/TAV_Assignments/TAV\ Assignment1/src/main/java/"

   #start terminal
   keyDown(Key.CMD+Key.SPACE)
   keyUp()
   paste("terminal")
   type(Key.ENTER)
   wait(1)

   #clear terminal
   type("k",Key.CMD)
   paste("cd ~")
   type(Key.ENTER)

   # change to correct path
   paste("cd " + path)
   type(Key.ENTER)

   # run GUI
   paste("java SimpleUI")
   type(Key.ENTER)
   wait(1)

   # check that GUI has actually started
   try:
       find(Pattern("1457010314872.png").similar(0.90))
        
   except:
       print "ERROR: GUI did not start correctly"
       return
   print "GUI started correctly"
   return

def quitGUI():
    type("q", Key.CMD)
    return


def enterVals(t, u, i):
    '''
       Enter the values t, u, i into the respective boxes.
       Assumes the GUI is already running.
    '''
    click(Pattern("1457010992664.png").similar(0.90).targetOffset(77,-1))
    paste(str(t))
    click(Pattern("1457011042273.png").similar(0.90).targetOffset(79,-1))
    paste(str(u))
    click(Pattern("1457011087133.png").similar(0.90).targetOffset(78,-3))
    paste(str(i))
    return

def startT():
    click(Pattern("1457012187279.png").similar(0.90))
    return
    

startGUI()
#enterVals(1,2,3)
#quitGUI()