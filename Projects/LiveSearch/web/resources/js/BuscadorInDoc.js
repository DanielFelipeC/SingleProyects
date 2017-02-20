/* global self */

if (
    parent.location==self.location
    && (""+self.location).toLowerCase().indexOf(".chm")==-1
    && (""+self.location).toLowerCase().indexOf("~hh")==-1
   )

var TRange=null

function findString (str) 
{

//document.f1.inicio.value=str;
//document.f1.inicio.focus()
 
 if (parseInt(navigator.appVersion)<4) return;
 var strFound;
 if (navigator.appName=="Netscape") {

  // Codigo Especifico Navegador

  strFound=self.find(str);
  if (!strFound) {
   strFound=self.find(str,0,1)
   while (self.find(str,0,1)) continue
  }
 }
 
 if (navigator.appName.indexOf("Microsoft")!=-1) 
 {

  // Codigo Especifico IE y demas

  if (TRange!=null) {
   TRange.collapse(false)
   strFound=TRange.findText(str)
   if (strFound) TRange.select()
  }
  if (TRange==null || strFound==0) {
   TRange=self.document.body.createTextRange()
   
   
   strFound=TRange.findText(str)
   if (strFound) {TRange.select();}
  }
 }
 if (!strFound) alert ("La palabra '"+str+"' no se ha podido encontrar!")
}

