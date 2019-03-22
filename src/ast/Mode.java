/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author mijail
 */
@XmlSeeAlso({InMode.class,OutMode.class,InOutMode.class})
public abstract class Mode {}