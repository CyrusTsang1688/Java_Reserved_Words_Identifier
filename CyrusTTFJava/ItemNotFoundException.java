/**
* class XRef - Cross Reference Map
*
* I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement
* and collusion. I am aware of the consequences if found to be involved in these misconducts. I hereby
* declare that the work submitted for the "ITP4510 Data Structures & Algorithms" is authentic record
* of my own work.
*
* @Name : Tsang Tsz Fung
* @StdID: 200539567
* @Class: IT114105/1A
* @2021-import java.io.*;
*/
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Item is not found!");
    }
}
