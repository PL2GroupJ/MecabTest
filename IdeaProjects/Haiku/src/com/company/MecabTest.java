package com.company;
import java.util.StringTokenizer;
import org.chasen.mecab.*;

import java.io.File;
public class MecabTest {
    static {
        try {
            File f = new File("/Users/haradakyouhei/Downloads/mecab-java/libMeCab.so"); // Select libMeCab.so path
            System.load(f.toString());
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
            System.exit(1);
        }
    }

    public static void main(String[] argv) {
        System.out.println(MeCab.VERSION);
        Tagger tagger = new Tagger();
        String str = "古池や蛙飛び込む水の音";

        /*一度の命令で解析*/
        System.out.println(tagger.parse(str));

        /*ノードごとに解析*/
        Node node = tagger.parseToNode(str);
        for (; node != null; node = node.getNext()) {
            System.out.println(node.getSurface() + "\t" + node.getFeature());
        }
        System.out.println("EOS\n");


        /*名刺の抽出*/
        Node nodeSplit = tagger.parseToNode(str);
        for (; nodeSplit != null; nodeSplit = nodeSplit.getNext()) {
            //System.out.println(nodeSplit.getSurface() + "\t" + nodeSplit.getFeature());
            String sf = nodeSplit.getSurface();
            String ft = nodeSplit.getFeature();
            StringTokenizer sta = new StringTokenizer(ft, ",");
            //トークンの出力
            while(sta.hasMoreTokens()) {
                if(sta.nextToken().equals("名詞")){
                    System.out.println(sf + "\t" + ft);
                }
            }

        }
        System.out.println("EOS\n");


        String[] haiku = {"古池や", "蛙飛び込む","水の音"};

        /*配列を解析*/
        int i;
        for(i = 0;i < haiku.length;i++) {
            System.out.println(tagger.parse(haiku[i]));
        }

        
        /*よくわからん*/
        /*Model model = new Model();
        Tagger tagger2 = model.createTagger();
        System.out.println(tagger2.parse(str));

        Lattice lattice = model.createLattice();
        System.out.println(str);
        lattice.set_sentence(str);
        if (tagger2.parse(lattice)) {
            System.out.println(lattice.toString());
            for (node = lattice.bos_node(); node != null; node = node.getNext()) {
                System.out.println(node.getSurface() + "\t" + node.getFeature());
            }
            System.out.println("EOS\n");
        }*/

        /*ここはいろんなパターンで行う*/
        /*lattice.add_request_type(MeCab.MECAB_NBEST);
        lattice.set_sentence(str);
        tagger2.parse(lattice);
        for (int i = 0; i < 10; ++i) {
            if (lattice.next()) {
                System.out.println("nbest:" + i + "\n" +
                        lattice.toString());
            }
        }*/



    }
}
