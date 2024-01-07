# sample_one.py

"""

A simple example showing how to use lib.plot from wxPython.
It is intended to be run as a standalone script via::
user@host:.../site-packages/wx/lib/plot$ python examples/simple_example.py

"""

import os
import sys
import wx
from wx.lib import plot as wxplot

# class MyPlotExample
# class MyApp

#---------------------------------------------------------------------------

class MyPlotExample(wx.Frame):
    def __init__(self):
        wx.Frame.__init__(self, None,
                          title="Example of wx.lib.plot",
                          size=(400, 300))

        #------------

        # Return icons folder.
        self.icons_dir = wx.GetApp().GetIconsDir()

        #------------
        
        # Generate some Data.
        x_data = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        y_data = [2, 4, 6, 4, 2, 5, 6, 7, 1]

        # Most items require data as a list of (x, y) pairs:
        #    [[1x, y1], [x2, y2], [x3, y3], ..., [xn, yn]]
        xy_data = list(zip(x_data, y_data))

        # Create your Poly object(s).
        # Use keyword args to set display properties.
        line = wxplot.PolySpline(
            xy_data,
            colour=wx.Colour(128, 128, 0),   # Color: olive
            width=3,
        )

        # Create your graphics object.
        graphics = wxplot.PlotGraphics([line])

        # Create your canvas.
        panel = wxplot.PlotCanvas(self)

        # Edit panel-wide settings.
        axes_pen = wx.Pen(wx.BLUE, 1, wx.PENSTYLE_LONG_DASH)
        panel.axesPen = axes_pen

        # Draw the graphics object on the canvas.
        panel.Draw(graphics)

        #------------
        
        # Create some sizers.
        mainSizer = wx.BoxSizer(wx.VERTICAL)
        checkSizer = wx.BoxSizer(wx.HORIZONTAL)

        # Layout the widgets.
        mainSizer.Add(panel, 1, wx.EXPAND | wx.ALL, 10)
        self.SetSizer(mainSizer)
        
        #------------
        
        # Simplified init method.
        self.SetProperties()
        
    #-----------------------------------------------------------------------

    def SetProperties(self):
        """
        ...
        """

        self.SetMinSize((400, 300))
        self.SetBackgroundColour(wx.WHITE)
        
        #------------

        frameIcon = wx.Icon(os.path.join(self.icons_dir,
                                         "wxwin.ico"),
                            type=wx.BITMAP_TYPE_ICO)
        self.SetIcon(frameIcon)       

#---------------------------------------------------------------------------

class MyApp(wx.App):    
    def OnInit(self):

        #------------

        self.installDir = os.path.split(os.path.abspath(sys.argv[0]))[0]
        
        #------------
        
        frame = MyPlotExample()
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
