function exportChart() {  
    //export image  
    $('#output').empty().append(Linearchart.exportAsImage());  
    //show the dialog  
    dlg.show();  
} 


