package bitcamp.java89.ems.typeTest;

import java.util.Scanner;

public class StageDB {
  Stage stage1;
  Stage stage2;
  Stage stage3;
  Stage stage4;
  Stage stage5;
  Stage stage6;
  Stage stage7;
  Stage stage8;
  Stage stage9;
  Stage stage10;
  Stage stage11;
  Stage stage12;
  Stage stage13;
  Stage stage14;
  Stage stage15;
  Stage stage16;
  Stage stage17;
  Stage stage18;
  Stage stage19;
  Stage stage20;
  Stage stage21;
  Stage stage22;
  Stage stage23;
  Stage stage24;
  Stage stage25;
  Stage stage26;
  Stage stage27;
  Stage stage28;
  Stage stage29;
  Stage stage30;
  Stage stage31;
  
  
  
  public class Stage {

    String question;  
    Stage yes;     
    Stage no; 
    
    public Stage(String question)
    {
      this.question = question;
    }
    
    public void setStage( Stage yes, Stage no)
    {
      this.yes = yes;
      this.no = no;
    }

    @Override
    public String toString() {
      return question;
    }

    public Stage getYes() {
      return yes;
    }

    public void setYes(Stage yes) {
      this.yes = yes;
    }

    public Stage getNo() {
      return no;
    }

    public void setNo(Stage no) {
      this.no = no;
    }
    
  }
  public void getQue() {
   
    stage1 = new Stage("정장보다는 캐주얼차림을 좋아한다?");
    stage2 = new Stage("학교나 직장에서 선배들에게 귀염움을 받는 편이다?");
    stage3 = new Stage("자기자신이 정신적으로 약하다고 느낄 때가 많다?");
    stage4 = new Stage("서예나 바둑등 차분한 것을 좋아하거나 배우고 있다?");
    stage5 = new Stage("친구나 친지의 생일을 메모하고 있거나 기억하고 있는 편이다?");
    stage6 = new Stage("경어사용에는 자신이 있다?");
    stage7 = new Stage("결혼식이 될 수 있으면 화려하게 하고 싶다?");
    stage8 = new Stage("친구에게 10만원이상 돈을 빌려준 적이 있다?");
    stage9 = new Stage("운동하는 것을 싫어하고 심지어는 괴로워한다?");
    stage10 = new Stage("판매원, 서비스업 등의 아르바이트를 해 본 경험이 있다?");
    stage11 = new Stage("동호회, 클럽 등의 활동을 좋아한다?");
    stage12 = new Stage("자기가 잘못을 하지 않았어도 분위기를 위하여 먼저 사과하는 적이 많다?");
    stage13 = new Stage("웃는 모습에 대하여 칭찬받은 적이 있다?");
    stage14 = new Stage("드라마, 영화, 만화 등에서 미스테리 장르를 즐긴다?");
    stage15 = new Stage("해외 어학연수를 생각해 본 적이 있다?");
    stage16 = new Stage("춤을 배운다면 어떤 춤을 재즈댄스 Yes, 사교댄스 No ?");
    stage17 = new Stage("자기 방에 있을 때 늘 TV나 MP3 등 뭔가를 틀어놓고 있다?");
    stage18 = new Stage("신문은 거의 읽지 않는다?");
    stage19 = new Stage("겨울스포츠로 하고 싶은 것 농구이면 Yes, 스키면 No ?");
    stage20 = new Stage("자기와 같은 옷을 친구가 세일에서 샀다고 하면 다시는 그 옷을 입지 않는다?");
    stage21 = new Stage("남의 말을 잘 들어주는 편이다?");
    stage22 = new Stage("술자리에서 옆 사람의 잔이 비어 있으면 곧바로 채워준다?");
    stage23 = new Stage("죽이고 싶다고 생각할 정도로 미워하는 사람이 있다?");
    stage24 = new Stage("모임에서 주로 리더가 되는 편이다?");
    stage25 = new Stage("외로움을 잘 타고 사람을 그리워 하는 편이다?");
    stage26 = new Stage("밝고 건강함이 당신의 매력");
    stage27 = new Stage("순진함이 당신의 매력");
    stage28 = new Stage("상냥함이 당신의 매력");
    stage29 = new Stage("여성스러움이 당신의 매력");
    stage30 = new Stage("신비스러움이 당신의 매력");
    stage31 = new Stage("지적인 분위기가 당신의 매력");
    
    stage1.setStage(stage6, stage2);
    stage2.setStage(stage7, stage3);
    stage3.setStage(stage8, stage4);
    stage4.setStage(stage9, stage5);
    stage5.setStage(stage6, stage10);
    stage6.setStage(stage7, stage11);
    stage7.setStage(stage12, stage8);
    stage8.setStage(stage9, stage13);
    stage9.setStage(stage14, stage10);
    stage10.setStage(stage11, stage15);
    stage11.setStage(stage16, stage12);
    stage12.setStage(stage17, stage13);
    stage13.setStage(stage18, stage14);
    stage14.setStage(stage15, stage19);
    stage15.setStage(stage16, stage19);
    stage16.setStage(stage21, stage17);
    stage17.setStage(stage22, stage18);
    stage18.setStage(stage23, stage19);
    stage19.setStage(stage24, stage20);
    stage20.setStage(stage21, stage25);
    stage21.setStage(stage22, stage26);
    stage22.setStage(stage27, stage23);
    stage23.setStage(stage24, stage28);
    stage24.setStage(stage25, stage29);
    stage25.setStage(stage31, stage30);
    
    stage26.setStage(null, null);
    stage27.setStage(null, null);
    stage28.setStage(null, null);
    stage29.setStage(null, null);
    stage30.setStage(null, null);
    stage31.setStage(null, null);
    
    
  }    
}
