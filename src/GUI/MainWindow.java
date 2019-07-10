package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.view.mxGraph;

import VKClient.VKClient;
import VKClient.VKUser;

public class MainWindow extends JFrame{
    private final int leftPanelWidth = 300;

    private JPanel toolBar;
    private JPanel buttonBar;
    private JPanel LeftPanel;

    private JList<String> mList;
    private JTextField InputField;

    private mxGraph graph;
    private Object parent;

    private VKClient mClient;
    private ArrayList<VKUser> mUsers;

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
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(InputField.getText());
                mUsers.add( mClient.getUser(id, null) );
                mList.setListData(VKUser.arrayToStrings(mUsers));
                // TODO: add vertex and edges
            }
        });
        JButton DelButton = new JButton("Delete user");
        DelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(InputField.getText());
                VKUser tmp = new VKUser(id, "", "");
                try {
                    VKUser.findAndRemove(mUsers, id);
                } catch (NoSuchElementException e)
                {
                    System.out.println("Exepc: " + e.getMessage());
                }
                mList.setListData(VKUser.arrayToStrings(mUsers));
                // TODO: remove vertex and edges
            }
        });

        JButton RefrButton = new JButton("Refresh graph");

        buttonBar.add(RefrButton);
        buttonBar.add(AddButton);
        buttonBar.add(DelButton);
        buttonBar.add(DeleteAll);
        buttonBar.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,400);

        toolBar.add( buttonBar, BorderLayout.WEST);
        getContentPane().add( toolBar, BorderLayout.NORTH);
    }

    protected void makeLeftPane(mxGraphComponent graphComponent){
        LeftPanel = new JPanel();
        JPanel panelForList = new JPanel();
        panelForList.setLayout(new BoxLayout(panelForList,BoxLayout.Y_AXIS));

        mList = new JList<>();
        mList.setFixedCellWidth(leftPanelWidth);
        mxGraphOutline graphOutline = new mxGraphOutline(graphComponent);
        graphOutline.setPreferredSize(new Dimension(leftPanelWidth,leftPanelWidth));
        InputField = new JTextField();
        InputField.setColumns(20);

        panelForList.add(InputField);
        panelForList.add(mList);

        LeftPanel.setLayout(new BorderLayout());
        LeftPanel.add( graphOutline, BorderLayout.PAGE_END);
        LeftPanel.add(panelForList, BorderLayout.PAGE_START);
        getContentPane().add(LeftPanel,BorderLayout.WEST);
    }

    private void setUpVKClient()
    {
        mClient = new VKClient();
        mUsers = new ArrayList<VKUser>();
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
        setUpVKClient();

        mxGraphComponent graphComponent= makeGraph();
        getContentPane().add(graphComponent, BorderLayout.CENTER);

        makeLeftPane(graphComponent);
        makeToolbar(graphComponent);

        setVisible(true);
    }
}
