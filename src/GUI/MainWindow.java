package GUI;


import java.awt.*;
import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.view.mxGraph;

public class MainWindow extends JFrame{
    private JPanel buttonBar;
    private String[] l = {"1","2"};
    private JPanel LeftPanel;
    private mxGraph graph;
    private JPanel toolBar;
    private Object parent;
    protected mxGraphComponent makeGraph(){
        graph = new mxGraph();
        parent = graph.getDefaultParent();

        final mxGraphComponent graphComponent = new mxGraphComponent(graph);
        return graphComponent;
    }


    protected void makeToolbar(mxGraphComponent graphComponent){
        toolBar = new JPanel();
        getContentPane().add(LeftPanel,BorderLayout.WEST);
        toolBar.setLayout( new BorderLayout());

        buttonBar = new JPanel();
        buttonBar.setLayout(new FlowLayout());
        JButton DeleteAll = new JButton( "Delete all");
        JButton AddButton = new JButton("Add user");
        JButton DelButton = new JButton("Delete user");
        JButton RefrButton = new JButton("Refresh graph");

        buttonBar.add(DeleteAll);
        buttonBar.add(RefrButton);
        buttonBar.add(AddButton);
        buttonBar.add(DelButton);
        buttonBar.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,400);


        toolBar.add( buttonBar, BorderLayout.WEST);
        getContentPane().add( toolBar, BorderLayout.NORTH);
    }


    protected void makeLeftPane(mxGraphComponent graphComponent){
        LeftPanel = new JPanel();
        JPanel panelForList = new JPanel();
        panelForList.setLayout(new BoxLayout(panelForList,BoxLayout.Y_AXIS));
        JList<String> list= new JList<String>(l);
        list.setFixedCellWidth(300);
        mxGraphOutline graphOutline = new mxGraphOutline(graphComponent);
        graphOutline.setPreferredSize(new Dimension(300,300));
        JTextField InputField = new JTextField();
        InputField.setColumns(20);

        panelForList.add(InputField);
        panelForList.add(list);

        LeftPanel.setLayout(new BorderLayout());
        LeftPanel.add( graphOutline, BorderLayout.PAGE_END);
        LeftPanel.add(panelForList, BorderLayout.PAGE_START);
        getContentPane().add(LeftPanel,BorderLayout.WEST);

    }


    public MainWindow() {
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
