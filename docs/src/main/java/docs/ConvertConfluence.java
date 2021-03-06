/*
 *    uDig - User Friendly Desktop Internet GIS client
 *    http://udig.refractions.net
 *    (C) 2012, Refractions Research Inc.
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package docs;

import java.io.File;

import javax.swing.JFileChooser;

public class ConvertConfluence {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if( args.length == 1 && "?".equals( args[0] )){
            System.out.println(" Usage: java docs.ConvertConfluence [entities.xml] [target directory]");
            System.out.println();
            System.out.println("Where:");
            System.out.println("  entities.xml Is produced when backing up a confluence wiki as xml");
            System.out.println("  target directory is the location to export the textile documents to");
            System.out.println();
            System.out.println("If not provided the appication will prompt you for the above information");
            
            System.exit(0);
        }
        File entitiesFile = args.length > 0 ? new File( args[0] ) : null;
        File targetDirectory = args.length > 1 ? new File( args[1] ) : null;

        if( entitiesFile != null && entitiesFile.isDirectory() ){
            entitiesFile = new File( entitiesFile, "entities.xml");
        }
        if( entitiesFile == null ){

            JFileChooser dialog = new JFileChooser( entitiesFile );
            dialog.setFileFilter( new javax.swing.filechooser.FileFilter() {
                public String getDescription() {
                    return "Confluence entities.xml export";
                }
                @Override
                public boolean accept(File f) {
                    return "entities.xml".equals( f.getName() );
                }
            });
            dialog.setDialogTitle("Convert entities.xml to textile");
            int open = dialog.showDialog( null, "Convert" );
            
            if( open == JFileChooser.CANCEL_OPTION ){
                System.out.println("Conversion canceled");
                System.exit(-1);
            }
            entitiesFile = dialog.getSelectedFile();
        }
        if ( entitiesFile == null || !entitiesFile.exists() || entitiesFile.isDirectory() ){
            System.out.println("File entities.xml to use for conversion not provided: '"+entitiesFile+"'");
            System.exit(-1);
        }

        if( targetDirectory == null ){
            JFileChooser dialog = new JFileChooser( targetDirectory );
            dialog.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            dialog.setDialogTitle("Target Directory for textile files");
            
            int open = dialog.showDialog( null, "Export" );
            
            if( open == JFileChooser.CANCEL_OPTION ){
                System.out.println("Canceled canceled");
                System.exit(-1);
            }
            targetDirectory = dialog.getSelectedFile();
        }
        if ( targetDirectory == null || !targetDirectory.isDirectory() || !targetDirectory.exists() ){
            System.out.println("Taget directory for textile files not provided: '"+targetDirectory+"'");
            System.exit(-1);
        }        
        ConfluenceDom myConfluenceDom = new ConfluenceDom( entitiesFile );
        
        myConfluenceDom.writeCurrentPagesTextile( targetDirectory );
    }

}
