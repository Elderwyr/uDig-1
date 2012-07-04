/* uDig - User Friendly Desktop Internet GIS client
 * http://udig.refractions.net
 * (C) 2010, Refractions Research Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
package net.refractions.udig.catalog.internal.wmt.ui.wizard.controls;

import net.refractions.udig.catalog.wmt.internal.Messages;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;

public class NASAControl extends WMTWizardControl {
    
    private ImageRegistry imageCache;
    private static final String IMG_NASA = "nasa.png"; //$NON-NLS-1$
    
    @Override
    protected Control buildControl(Composite composite) {
        Composite control = new Composite(composite, SWT.NONE);
        control.setLayout(new RowLayout(SWT.VERTICAL));
        
        Label text = new Label(control, SWT.HORIZONTAL | SWT.WRAP);
        text.setLayoutData(new RowData(400, 60));
        text.setText(Messages.Wizard_Nasa_Info);
        
        Link link = new Link(control, SWT.BORDER);
        link.setText(Messages.Wizard_Nasa_InfoLink);
        link.setLayoutData(new RowData(400, 40));
        link.addListener (SWT.Selection, new Listener () {
            public void handleEvent(Event event) {
                Program.launch("http://onearth.jpl.nasa.gov/tiled.html"); //$NON-NLS-1$
            }
        });

        imageCache = new ImageRegistry(composite.getDisplay());
        ImageDescriptor desc = ImageDescriptor.createFromFile(getClass(), 
                NASAControl.IMG_NASA);
        imageCache.put(NASAControl.IMG_NASA, desc);
        
        Composite imgControl = new Composite(control, SWT.NONE);
        imgControl.setLayoutData(new RowData(300, 100));
        imgControl.setBackgroundImage(imageCache.get(NASAControl.IMG_NASA));
        
        this.control = control;
        
        return control;
    }

}
