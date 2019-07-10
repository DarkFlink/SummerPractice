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
    private final int colNum = 20;
    private JPanel toolBar;
    private JPanel buttonBar;
    private JPanel LeftPanel;
    private final Color colorForTools = new Color(55,55,55);
    private final Color colorForOther = new Color(200,200,200);
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

    protected void makeToolbar(){
        toolBar = new JPanel();
        getContentPane().add(LeftPanel,BorderLayout.WEST);
        toolBar.setLayout( new BorderLayout());
        toolBar.setBackground(colorForTools);

        buttonBar = new JPanel();
        buttonBar.setLayout(new FlowLayout());
        buttonBar.setBackground(colorForTools);

        ImageIcon resImg = new ImageIcon(System.getProperty("user.dir")+"/assets/Icons/graphreset.png");
        JButton DeleteAll = new JButton( "",resImg);
        DeleteAll.setBackground(colorForTools);
        ImageIcon addImg = new ImageIcon(System.getProperty("user.dir")+"/assets/Icons/add.png");
        JButton AddButton = new JButton("", addImg);
        AddButton.setBackground(colorForTools);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(InputField.getText());
                mUsers.add( mClient.getUser(id, null) );
                mList.setListData(VKUser.arrayToStrings(mUsers));
                // TODO: add vertex and edges
            }
        });
        ImageIcon delImg = new ImageIcon(System.getProperty("user.dir")+"/assets/Icons/erase.png");
        JButton DelButton = new JButton("",delImg);
        DelButton.setBackground(colorForTools);
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
        ImageIcon refrImg = new ImageIcon(System.getProperty("user.dir")+"/assets/Icons/graphrefresh.png");
        JButton RefrButton = new JButton("",refrImg);
        RefrButton.setBackground(colorForTools);
        RefrButton.setSize(new Dimension(1000,1000));

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
        LeftPanel.setBackground(colorForTools);
        JPanel panelForList = new JPanel();
        panelForList.setLayout(new BoxLayout(panelForList,BoxLayout.Y_AXIS));
        panelForList.setBackground(colorForTools);

        mList = new JList<>();
        mList.setFixedCellWidth(leftPanelWidth);
        mList.setBackground(colorForTools);
        mxGraphOutline graphOutline = new mxGraphOutline(graphComponent);
        graphOutline.setPreferredSize(new Dimension(leftPanelWidth,leftPanelWidth));
        InputField = new JTextField();
        InputField.setColumns(colNum);
        InputField.setBackground(colorForOther);

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
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(colorForOther);
        getContentPane().setBackground(colorForTools);
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        makeLeftPane(graphComponent);
        makeToolbar();

        setVisible(true);
    }
}
