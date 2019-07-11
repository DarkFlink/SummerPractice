package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import VKClient.VKUser;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.*;

public class UserInfoFrame extends JFrame{
    private mxGraph graph;
    private DefaultListModel<VKUser> list;
    private String id;
    private final Color color = new Color(55,55,55);
    private final Color textColor = new Color(200,200,200);
    private mxIGraphLayout layout;

    public UserInfoFrame(mxGraph mxGraph, mxIGraphLayout mlayout, DefaultListModel<VKUser> mlist, String user_id) {
        graph = mxGraph;
        list = mlist;
        id = user_id;
        layout=mlayout;
        setTitle("User Info");
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(400, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        getContentPane().setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setBackground(color);
        add(northPanel,BorderLayout.NORTH);

        ImageIcon delImg = new ImageIcon(System.getProperty("user.dir")+"/assets/Icons/erase.png");
        JButton delUser=new JButton("",delImg);
        delUser.setBackground(color);
        delUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delVertex(id);
                delVtxFromList(id);
                dispose();
            }
        });

        northPanel.setLayout(new BorderLayout());
        northPanel.add(delUser,BorderLayout.EAST);

        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(color);
        add(centrePanel,BorderLayout.CENTER);
        getContentPane().setBackground(color);
        JTextArea textField=new JTextArea("efwe");
        textField.setEnabled(false);
        textField.setBackground(color);
        textField.setForeground(textColor);
        textField.setPreferredSize(new Dimension(300,300));
        centrePanel.add(textField,BorderLayout.CENTER);
        setVisible(true);
    }

    private void delVertex(String id){
        graph.getModel().beginUpdate();
        try {
            Object[] arr=graph.getChildVertices(graph.getDefaultParent());
            for (Object c: arr) {
                mxCell vertex=(mxCell)c;
                if(id.equals(vertex.getId())) {
                    graph.removeCells(graph.getEdges(vertex));
                    graph.getModel().remove(vertex);
                }
            }

        }
        finally {
            layout.execute(graph.getDefaultParent());
            delVtxFromList(id);
            graph.getModel().endUpdate();
        }
    }

    public void delVtxFromList(String id)
    {
        for(int i=0; i<list.size(); i++)
            if(list.get(i).userId == Integer.parseInt(id))
                list.remove(i);
    }
}
