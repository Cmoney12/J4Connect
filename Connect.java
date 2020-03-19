import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


class Distance {

    //dropping list
    public ArrayList<Point> twolist;
    public ArrayList<Point> twoSixty;
    public ArrayList<Point> threetwenty;
    public ArrayList<Point> threeEighty;
    public ArrayList<Point> fourForty;
    public ArrayList<Point> fiveHundred;
    public ArrayList<Point> fiveSixty;
    public ArrayList<Point> dead;
    public Point my_point;
    public boolean alreadyExecuted;
    public int turns;
    private List<Point> player;

    public ArrayList<Point> onePosition;
    public ArrayList<Point> twoPosition;

    public int win;
    public int rowNum;
    public int colNum;

    public void Lists() {
        if (!alreadyExecuted) {
            System.out.println("Ran once ");
            twolist = new ArrayList<>();
            twoSixty = new ArrayList<>();
            threetwenty = new ArrayList<>();
            threeEighty = new ArrayList<>();
            fourForty = new ArrayList<>();
            fiveHundred = new ArrayList<>();
            fiveSixty = new ArrayList<>();
            onePosition = new ArrayList<>();
            twoPosition = new ArrayList<>();
            for (int x4 = 200; x4 < 620; x4 += 60) {
                for (int y4 = 200; y4 < 560; y4 += 60) {
                    if (x4 == 200) {
                        twolist.add(new Point(x4, y4));
                    } else if (x4 == 260) {
                        twoSixty.add(new Point(x4, y4));
                    } else if (x4 == 320) {
                        threetwenty.add(new Point(x4, y4));
                    } else if (x4 == 380) {
                        threeEighty.add(new Point(x4, y4));
                    } else if (x4 == 440) {
                        fourForty.add(new Point(x4, y4));
                    } else if (x4 == 500) {
                        fiveHundred.add(new Point(x4, y4));
                    } else if (x4 == 560) {
                        fiveSixty.add(new Point(x4, y4));
                    }
                    alreadyExecuted = true;
                }
            }

        }
    }

    public void Values(int releasedx) {
        dead = new ArrayList<>();

        if (releasedx <= 225 && !twolist.isEmpty()) {
            my_point = twolist.get(twolist.size() - 1);
            twolist.remove(twolist.size() - 1);

        } else if (releasedx <= 285 && !twoSixty.isEmpty()) {
            my_point = twoSixty.get(twoSixty.size() - 1);
            twoSixty.remove(twoSixty.size() - 1);

        } else if (releasedx <= 350 && !threetwenty.isEmpty()) {
            my_point = threetwenty.get(threetwenty.size() - 1);
            threetwenty.remove(threetwenty.size() - 1);

        } else if (releasedx <= 390 && !threeEighty.isEmpty()) {
            my_point = threeEighty.get(threeEighty.size() - 1);
            threeEighty.remove(threeEighty.size() - 1);

        } else if (releasedx <= 465 && !fourForty.isEmpty()) {
            my_point = fourForty.get(fourForty.size() - 1);
            fourForty.remove(fourForty.size() - 1);

        } else if (releasedx <= 525 && !fiveHundred.isEmpty()) {
            my_point = fiveHundred.get(fiveHundred.size() - 1);
            fiveHundred.remove(fiveHundred.size() - 1);

        } else if (releasedx <= 560 && !fiveSixty.isEmpty()) {
            my_point = fiveSixty.get(fiveSixty.size() - 1);
            fiveSixty.remove(fiveSixty.size() - 1);
        }
        if (turns == 0) {
            onePosition.add(my_point);
            rowNum = my_point.x;
            colNum = my_point.y;
            turns++;
        } else if (turns == 1) {
            rowNum = my_point.x;
            colNum = my_point.y;
            twoPosition.add(my_point);
            turns--;
        }
    }

    public double getLastX() {
        return my_point.getX();
    }

    public double getLastY() {
        return my_point.getY();
    }

    public void checkWinner() {
        int maxRow = 560;
        int maxCol = 620;
        //player ID
        int count = 0;
        int rowStart;
        ArrayList<Point> gridTable = new ArrayList<>();

        if (turns == 1) {
            player = new ArrayList<>(onePosition);
            player.addAll(onePosition);
        } else if (turns == 0) {
            player = new ArrayList<>(twoPosition);
            player.addAll(twoPosition);
        }

        // verticle check
        for (int i = 200; i < maxCol; i += 60) {
            gridTable.add(new Point(rowNum, i));
            for (Point w : gridTable) {
                if (player.contains(w)) {
                    count++;
                    if (count >= 4) {
                        win = 1;
                        System.out.println("verticle win");
                    }
                }
            }
            gridTable.clear();
        }
        if (count < 4) {
            count = 0;
        }

        for (int i = 200; i < maxRow; i += 60) {
            Point point = new Point(i, colNum);
            gridTable.add(point);
            for (Point w : gridTable) {
                if (player.contains(w)) {
                    count++;
                    if (count >= 4) {
                        win = 1;
                        System.out.println("Horizontal win");
                    }
                }
            } if(count < 4) {
                count = 0;
            }
        } gridTable.clear();


        // top-left to bottom-right - green diagonals
        for (rowStart = 200; rowStart < maxRow - (4 * 60); rowStart += 60) {
            int row, col;
            for (row = rowStart, col = 200; row < maxRow && col < maxCol; row += 60, col += 60) {
                Point point = new Point(row, col);
                gridTable.add(point);
                for (Point w : gridTable) {
                    if (player.contains(w)) {
                        count++;
                        if (count >= 4) {
                            win = 1;
                            System.out.println("top left winner");
                        }
                    }
                } if(count < 4) {
                    count = 0;
                }
            }
        } gridTable.clear();

        // top-left to bottom-right - red diagonals
        for(int colStart = 200; rowStart < maxCol - (4 * 60); rowStart+=60) {
            int row, col;
            for (row = rowStart, col = colStart; row < maxRow && col < maxCol; row += 60, col += 60) {
                Point point = new Point(row, col);
                gridTable.add(point);
                for (Point w : gridTable) {
                    //for (Point j : player) {
                    if (player.contains(w)) {
                        count++;
                        if (count >= 4) {
                            win = 1;
                            System.out.println("top left");
                        }
                    }
                }
            } if(count < 0) {
                count = 0;
            }
        }
        gridTable.clear();
    }


    public int getWin() {
        return win;
    }

}

    public class Connect extends JPanel implements MouseListener, MouseMotionListener {

        int x;
        int y;
        public Point2D.Double offSet;
        Graphics2D g2;
        public ArrayList<Ellipse2D.Double> player1Ovals;
        public ArrayList<Ellipse2D.Double> player2Ovals;
        public ArrayList<Shape> my_board;
        public ArrayList<Integer> boardCoords;
        public ArrayList<Point> points;
        public Distance distance = new Distance();
        private int player;
        public JLabel label = new JLabel();

        double x1, y1, x2, y2, size;

        public Connect() {

            player1Ovals = new ArrayList<>();
            player2Ovals = new ArrayList<>();
            my_board = new ArrayList<>();
            boardCoords = new ArrayList<>();
            points = new ArrayList<>();

            size = 50.0;
            x2 = x1 + size;
            y2 = y1 + size;
            //list one red player 1
            for (x = 10; x < 190; x +=60) {
                for (y = 10; y < 420; y+=60) {
                    player1Ovals.add(new Ellipse2D.Double(x, y, size, size));
                }
            }
            // list two yellow player 2
            for (int x3 = 670; x3 < 920; x3 += 60) {
                for(int y3 = 90; y3 < 510; y3+=60) {
                    player2Ovals.add(new Ellipse2D.Double(x3, y3, size, size));
                }
            }

            double rSize = 60.0;
            double eSize = 50.0;
            // the board
            for (double x4 = 200.0; x4 < 620; x4 += 60.0) {
                for (double y4 = 200.0; y4 < 560; y4 += 60.0) {

                    Area a1 = new Area(new Rectangle2D.Double(x4, y4, rSize, rSize));
                    Area a2 = new Area(new Ellipse2D.Double(x4, y4, eSize, eSize));
                    a1.subtract(a2);
                    my_board.add(a1);
                    int x5 = (int) x4;
                    int y5 = (int) y4;
                    points.add(new Point(x5, y5));
                }
            }
            label.setLocation(10,10);
            add(label);
            JLabel title = new JLabel("Connect 4");
            title.setFont(new Font("Serif", Font.BOLD, 50));
            title.setLocation(20, 20);
            title.setForeground(Color.BLUE);
            setFocusable(true);
            add(title);
            addMouseListener(this);
            addMouseMotionListener(this);
            this.requestFocus();
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            for (Ellipse2D i : player1Ovals) {
                g2.fill(i);
            }
            g2.setColor(Color.BLUE);
            for (Shape i : my_board) {
                g2.fill(i);
            }
            g2.setColor(Color.yellow);
            for(Shape i : player2Ovals) {
                g2.fill(i);
            }
        }

        private Ellipse2D selected = null;
        public Ellipse2D.Double original_position;
        public int originalx;
        public int originaly;
        public int releasedx, releasedy;

        public void labelText() {
            if(player == 0) {
                label.setText("Reds Turn");
            }
            else if(player == 1) {
                label.setText("Yellows Turn");
            }
            if(player == 1 && distance.getWin() == 1) {
                label.setText("Red Wins");
                label.setFont(new Font("Serif", Font.BOLD, 30));
                label.setForeground(Color.RED);
            }
            else if(player == 0 && distance.getWin() == 1) {
                label.setText("Yellow Wins");
                label.setFont(new Font("Serif", Font.BOLD, 30));
                label.setForeground(Color.yellow);
            }
        }
        public void printer() {
            if(distance.getWin() == 1) {
                System.out.println("Winner ");
            } else {
                System.out.println("No winner");
            }
        }

        @Override
        public void mousePressed(MouseEvent ev) {
            x = ev.getX();
            y = ev.getY();
            int x2 = x;
            int y2 = y;
            originalx = x2;
            originaly = y2;
            if (player == 0) {
                List<Ellipse2D.Double> all = new ArrayList<>(player1Ovals);
                all.addAll(player1Ovals);
                for (Ellipse2D.Double i : all) {
                    if (i.contains(x, y)) {
                        selected = i;
                        original_position = i;
                        offSet = new Point2D.Double(x - i.getX(), y - i.getY());
                        player++;
                        labelText();
                        break;
                    }
                }
            }

            else if(player == 1){
                List<Ellipse2D.Double> list = new ArrayList<>(player2Ovals);
                list.addAll(player2Ovals);
                for(Ellipse2D.Double i : list) {
                    if (i.contains(x, y)) {
                        selected = i;
                        original_position = i;
                        offSet = new Point2D.Double(x - i.getX(), y - i.getY());
                        player--;
                        labelText();
                        break;
                    }
                }
            } System.out.println(player);
        }

        @Override
        public void mouseDragged(MouseEvent ev) {
            if (selected != null) {

                double x = ev.getX() - offSet.x;
                double y = ev.getY() - offSet.y;

                Rectangle2D bounds = selected.getBounds2D();
                bounds.setFrame(new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight()));

                selected.setFrame(bounds);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("x: " + (e.getX()) + " y: " + (e.getY()));
        }

        @Override
        public void mouseReleased(MouseEvent ev) {
            releasedx = ev.getX();
            releasedy = ev.getY();

            distance.Lists();
            distance.Values(releasedx);
            distance.checkWinner();
            printer();

            Rectangle2D bounds = selected.getBounds2D();
            bounds.setFrame(new Rectangle2D.Double(distance.getLastX(), distance.getLastY(), bounds.getWidth(), bounds.getHeight()));
            selected.setFrame(bounds);
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        public static void main(String[] args) {
            JFrame window = new JFrame();
            window.setSize(950, 950);
            window.setVisible(true);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.add(new Connect());
        }
}
