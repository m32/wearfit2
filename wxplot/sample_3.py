# sample_three.py

"""

Author : Mike Driscoll
Created :  Sept. 22, 2010
Updated : Octo. 5, 2020 by Ecco
Link : https://www.blog.pythonlibrary.org/2010/09/27/wxpython-pyplot-graphs-with-python/

"""

import os
import sys
import wx
from wx.lib.plot import PolyLine, PlotCanvas, PlotGraphics

# class MyGraph
# class MyApp

#---------------------------------------------------------------------------

class MyGraph(wx.Frame):
    def __init__(self):
        wx.Frame.__init__(self, None, wx.ID_ANY, 
                          'Plotting file data')

        #------------

        # Return icons folder.
        self.icons_dir = wx.GetApp().GetIconsDir()

        #------------
        
        # Add a panel so it looks the correct on all platforms.
        panel = wx.Panel(self, wx.ID_ANY)

        self.canvas = PlotCanvas(panel)
        self.canvas.Draw(self.CreatePlotGraphics())

        toggleGrid = wx.CheckBox(panel, label="Show Grid")
        toggleGrid.SetValue(True)
        toggleGrid.Bind(wx.EVT_CHECKBOX, self.OnToggleGrid)
        
        #------------
        
        sizer = wx.BoxSizer(wx.VERTICAL)        
        checkSizer = wx.BoxSizer(wx.HORIZONTAL)
        
        sizer.Add(self.canvas, 1, wx.EXPAND | wx.ALL, 10)
        checkSizer.Add(toggleGrid, 0, wx.ALL, 5)
        sizer.Add(checkSizer)
        panel.SetSizer(sizer)

        #------------
        
        # Simplified init method.
        self.SetProperties()
        
    #-----------------------------------------------------------------------

    def SetProperties(self):
        """
        ...
        """

        self.SetMinSize((450, 360))
        self.SetBackgroundColour(wx.WHITE)
        
        #------------

        frameIcon = wx.Icon(os.path.join(self.icons_dir,
                                         "wxwin.ico"),
                            type=wx.BITMAP_TYPE_ICO)
        self.SetIcon(frameIcon)
        
        
    def ReadFile(self):
        """
        ...
        """
        
        # Normally you would want to pass a file path in, NOT hard code it !
        f = open("data.txt")
        # Skip the first two lines of text in the file.
        data = f.readlines()[2:-1]
        temps = []
        for line in data:
            parts = line.split(",")
            date = parts[0].split("-")
            day = date[2]
            points = [(day, parts[3]), (day, parts[1])]
            temps.append(points)
        return temps
        

    def CreatePlotGraphics(self):
        """
        ...
        """
        
        temps = self.ReadFile()
        lines = []
        for temp in temps:
            tempInt = int(temp[1][1])
            if tempInt < 60:
                color = "blue"
            elif tempInt >=60 and tempInt <= 75:
                color = "orange"
            else:
                color = "red"
            lines.append(PolyLine(temp, colour=color, width=10))
            
        return PlotGraphics(lines, "Bar Graph of Temperatures", 
                            "Days", "Temperatures")


    def OnToggleGrid(self, event):
        """
        ...
        """
        
        # self.canvas.SetEnableGrid(event.IsChecked())
        self.canvas.enableGrid = event.IsChecked()

#---------------------------------------------------------------------------

class MyApp(wx.App):    
    def OnInit(self):

        #------------

        self.installDir = os.path.split(os.path.abspath(sys.argv[0]))[0]
        
        #------------
        
        frame = MyGraph()
        self.SetTopWindow(frame)
        frame.Show(True)

        return True
    
    #-----------------------------------------------------------------------
    
    def GetInstallDir(self):
        """
        Return the installation directory for my application.
        """

        return self.installDir


    def GetIconsDir(self):
        """
        Return the icons directory for my application.
        """

        icons_dir = os.path.join(self.installDir, "icons")
        return icons_dir
    
#---------------------------------------------------------------------------

def main():
    app = MyApp(False)
    app.MainLoop()

#---------------------------------------------------------------------------

if __name__ == "__main__" :
    main()
