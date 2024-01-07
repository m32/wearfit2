# sample_two.py

"""

Author : Mike Driscoll
Created : Sept. 22, 2010
Updated : Octo. 5, 2020 by Ecco
Link : https://www.blog.pythonlibrary.org/2010/09/27/wxpython-pyplot-graphs-with-python/

"""

import os
import sys
import wx
from wx.lib.plot import PolyLine, PlotCanvas, PlotGraphics

# def drawBarGraph
# class MyGraph
# class MyApp

#---------------------------------------------------------------------------

def drawBarGraph():
    # Bar graph
    points1=[(1,0), (1,10)]
    line1 = PolyLine(points1, colour='green', legend='Feb.', width=10)
    points1g=[(2,0), (2,4)]
    line1g = PolyLine(points1g, colour='red', legend='Mar.', width=10)
    points1b=[(3,0), (3,6)]
    line1b = PolyLine(points1b, colour='blue', legend='Apr.', width=10)
    points2=[(4,0), (4,12)]
    line2 = PolyLine(points2, colour='Yellow', legend='May', width=10)
    points2g=[(5,0), (5,8)]
    line2g = PolyLine(points2g, colour='orange', legend='June', width=10)
    points2b=[(6,0), (6,4)]
    line2b = PolyLine(points2b, colour='brown', legend='July', width=10)
    return PlotGraphics([line1, line1g, line1b, line2, line2g, line2b],
                        "Bar Graph - (Turn on Grid, Legend)", "Months", 
                        "Number of Students")

#---------------------------------------------------------------------------

class MyGraph(wx.Frame):
    def __init__(self):
        wx.Frame.__init__(self, None, wx.ID_ANY, 
                          'My first plot (to take over the world !)')

        #------------

        # Return icons folder.
        self.icons_dir = wx.GetApp().GetIconsDir()

        #------------
        
        # Add a panel so it looks the correct on all platforms.
        panel = wx.Panel(self, wx.ID_ANY)
        
        #------------
        
        # Create some sizers.
        mainSizer = wx.BoxSizer(wx.VERTICAL)
        checkSizer = wx.BoxSizer(wx.HORIZONTAL)

        #------------
        
        # Create the widgets.
        self.canvas = PlotCanvas(panel)
        self.canvas.Draw(drawBarGraph())
        
        toggleGrid = wx.CheckBox(panel, label="Show Grid")
        toggleGrid.SetValue(True)
        toggleGrid.Bind(wx.EVT_CHECKBOX, self.OnToggleGrid)
        
        toggleLegend = wx.CheckBox(panel, label="Show Legend")
        toggleLegend.SetValue(False)
        toggleLegend.Bind(wx.EVT_CHECKBOX, self.OnToggleLegend)

        #------------
        
        # Layout the widgets.
        mainSizer.Add(self.canvas, 1, wx.EXPAND | wx.ALL, 10)
        checkSizer.Add(toggleGrid, 0, wx.ALL, 5)
        checkSizer.Add(toggleLegend, 0, wx.ALL, 5)
        mainSizer.Add(checkSizer)
        panel.SetSizer(mainSizer)

        #------------
        
        # Simplified init method.
        self.SetProperties()
        
    #-----------------------------------------------------------------------

    def SetProperties(self):
        """
        ...
        """

        self.SetMinSize((450, 350))
        self.SetBackgroundColour(wx.WHITE)
        
        #------------

        frameIcon = wx.Icon(os.path.join(self.icons_dir,
                                         "wxwin.ico"),
                            type=wx.BITMAP_TYPE_ICO)
        self.SetIcon(frameIcon)

        
    def OnToggleGrid(self, event):
        """
        ...
        """
        
        # self.canvas.SetEnableGrid(event.IsChecked())
        self.canvas.enableGrid = event.IsChecked()
        

    def OnToggleLegend(self, event):
        """
        ...
        """
        
        # self.canvas.SetEnableLegend(event.IsChecked())
        self.canvas.enableLegend = event.IsChecked()
        
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


