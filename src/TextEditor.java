import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //frame for textEditor
    JFrame frame;
    //menubar for containing menu
    JMenuBar menuBar;
    //menus
    JMenu file,edit;
    //item for filemenu
    JMenuItem newfile,savefile,openfile;
    //item for editmenu
    JMenuItem cut,copy,paste,selectAll,close;
    //Area for writing text
    JTextArea textArea;

    TextEditor(){
        //Initialise frame
        frame=new JFrame();
        //Initialise menubar
        menuBar=new JMenuBar();
        //Initialise menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //Initialise menuitem for file menu
        newfile=new JMenuItem("New");
        openfile=new JMenuItem("Open");
        savefile=new JMenuItem("Save");

        //Add ActionListener in file menu item
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        //Adding menu item to filemenu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        // Initialise MenuItem for edit menu
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("SelectAll");
        close=new JMenuItem("Close");

        //Add Action Listener to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding menu item to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menu to menubar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        //Initialise textArea
        textArea=new JTextArea();

        //Adding textArea to frame
        frame.add(textArea);
        //set the Dimention for frame
        frame.setBounds(100,100,800,500);
        //mark frame as unhidden
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        //if source is new
        if(actionEvent.getSource()==newfile){
            TextEditor newWindow=new TextEditor();
        }
        if(actionEvent.getSource()==openfile){
            //Initialise file chooser
            JFileChooser fileChooser=new JFileChooser("D:");
            //Get Choose option from file chooser
            int ChooseOption=fileChooser.showOpenDialog(null);
            //choose option is equal to approve
            if(ChooseOption==JFileChooser.APPROVE_OPTION){
                 File file=fileChooser.getSelectedFile();
                //Get selected file path
                String filePath=file.getPath();

                try {
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    //create string to store content from file
                    String intermediate="", output="";
                    //Read content line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output = output + intermediate+"\n";
                    }
                    //Get content to text area
                    textArea.setText(output);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
       if(actionEvent.getSource()==savefile){
           //save a file
           //create file
           JFileChooser fileChooser=new JFileChooser("C:");
           //Get chooser option from file chooser
           int chooseOption=fileChooser.showSaveDialog(null);
           //if choosen option is approve
           if(chooseOption==JFileChooser.APPROVE_OPTION){
               File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+"txt");
               try {
                   //create bufferwriter to write content of file
                   BufferedWriter outfile=new BufferedWriter(new FileWriter(file));
                   //Get content from text area to outfile
                   textArea.write(outfile);
                   outfile.close();

               }
               catch (Exception exception){
                   exception.printStackTrace();
               }
           }
       }
    }
    public static void main(String[] args) {
      TextEditor textEditor = new TextEditor();
    }
}