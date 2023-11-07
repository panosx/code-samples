package com.group20;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Implements user mouse inputs.
 */
public class ClickButton extends JLabel implements MouseListener{
    ActionListener myAL;
    public ClickButton(String str){
        super(str);
        this.setForeground(Color.blue);
        this.setOpaque(true);
        this.addMouseListener(this);
        setFont(new Font("Serif", Font.PLAIN, 60));
    }
    
    /** Adds an action listener to listen for mouse input.
     * @param al: mouse input
     */
    public void addActionListener(ActionListener al){
        myAL = al;
    }

    
    /** Invoked on mouse click.
     * @param e: mouse input
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        myAL.actionPerformed(new ActionEvent(this,501,""));
    }

    
    /** Invoked on a mouse press (mouse is clicked down, but not released.)
     * @param e: mouse input
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    
    /** Invoked on a mouse button release.
     * @param e: mouse input
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    
    /** Invoked when the mouse cursor enters a component.
     * @param e: mouse input
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(new Color(243, 105, 66));
    }

    
    /** Invoked when the mouse cursor leaves a component.
     * @param e: mouse input
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(Color.blue);
    }
}
