package GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.view.mxGraph;

public class Graph extends JFrame{
    private JPanel buttonBar;
    private String[] l = {"1","2"};
    private JPanel LeftPanel;
    private mxGraph graph;
    private JPanel toolBar;

    protected mxGraphComponent makeGraph(){
        graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "node1", 100, 100, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "node2", 1000, 100, 80, 30);
            Object v3 = graph.insertVertex(parent, null, "node3", 100, 1000, 80, 30);

            graph.insertEdge(parent, null, "Edge", v1, v2);
            graph.insertEdge(parent, null, "Edge", v2, v3);

        } finally {
            graph.getModel().endUpdate();
        }
        final mxGraphComponent graphComponent = new mxGraphComponent(graph);
        return graphComponent;
    }


    protected void makeToolbar(mxGraphComponent graphComponent){
        toolBar = new JPanel();
        getContentPane().add(LeftPanel,BorderLayout.WEST);
        toolBar.setLayout( new BorderLayout());

        buttonBar = new JPanel();
        buttonBar.setLayout(new FlowLayout());
        JButton btResetOutline = new JButton( "Reset Outline");
        JButton AddButton = new JButton("Add user");
        btResetOutline.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                graphComponent.getGraphControl().scrollRectToVisible(new Rectangle(0,0,0,0));
            }
        });
        buttonBar.add( btResetOutline);
        buttonBar.add(AddButton);
        buttonBar.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,400);


        toolBar.add( buttonBar, BorderLayout.WEST);
        getContentPane().add( toolBar, BorderLayout.NORTH);
    }


    protected void makeLeftPane(mxGraphComponent graphComponent){
        LeftPanel = new JPanel();
        JList<String> list= new JList<String>(l);
        list.setFixedCellWidth(300);
        mxGraphOutline graphOutline = new mxGraphOutline(graphComponent);
        graphOutline.setPreferredSize(new Dimension(300,300));
        LeftPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        LeftPanel.setLayout(new BorderLayout());
        LeftPanel.add( graphOutline, BorderLayout.PAGE_END);
        LeftPanel.add(list, BorderLayout.LINE_START);
        getContentPane().add(LeftPanel,BorderLayout.WEST);

    }


    public Graph() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e){
            //throw e;
        }
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        mxGraphComponent graphComponent= makeGraph();
        getContentPane().add(graphComponent, BorderLayout.CENTER);


        makeLeftPane(graphComponent);
        makeToolbar(graphComponent);

        setVisible(true);

    }

}
