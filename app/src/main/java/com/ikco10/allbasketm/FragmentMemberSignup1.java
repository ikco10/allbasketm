package com.ikco10.allbasketm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.animsh.animatedcheckbox.AnimatedCheckBox;
import com.ikco10.allbasketm.FastScroller.FastScrollScrollView;
import com.ikco10.allbasketm.FastScroller.FastScrollerBuilder;
import com.ikco10.allbasketm.Utils.OnSingleClickListener;

import java.util.Objects;

@SuppressLint("ValidFragment")
public class FragmentMemberSignup1 extends Fragment {

    Context mContext = MainActivity.getAppContext();
    private AnimatedCheckBox checkBoxAll;
    private AnimatedCheckBox checkBox1;
    private AnimatedCheckBox checkBox2;
    private AnimatedCheckBox checkBox3;
    private Button next;

    FragmentMemberSignup1() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_membersignup1, container, false);

        FastScrollScrollView scrollView1 = view.findViewById(R.id.scrollview1);
        FastScrollScrollView scrollView2 = view.findViewById(R.id.scrollview2);
        TextView termsTV1 = view.findViewById(R.id.terms1);
        TextView termsTV2 = view.findViewById(R.id.terms2);
        ImageButton expand1 = view.findViewById(R.id.expand_terms1);
        ImageButton expand2 = view.findViewById(R.id.expand_terms2);
        checkBoxAll = view.findViewById(R.id.checkall);
        checkBox1 = view.findViewById(R.id.check1);
        checkBox2 = view.findViewById(R.id.check2);
        checkBox3 = view.findViewById(R.id.check3);
        next = view.findViewById(R.id.next);

        StringBuilder terms1 = new StringBuilder();
        StringBuilder terms2 = new StringBuilder();

        new FastScrollerBuilder(scrollView1).setTrackDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.ic_track))).build();
        new FastScrollerBuilder(scrollView2).setTrackDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.ic_track))).build();

        terms1.append("올바스켓 멤버십 이용약관\n" +
                "\n" +
                "제1장 총칙\n" +
                "\n" +
                "제1조 (목적)\n" +
                "본 약관은 올바스켓(이하 \"당사\")에서 운영하는 \"올바스켓 멤버십 서비스\"의 이용과 관련하여 당사와 회원의 제반 권리, 의무, 관련 절차 등을 규정하는데 그 목적이 있습니다.\n" +
                "\n" +
                "제2조 (정의)\n" +
                "(1) 멤버십 회원(이하 \"회원\"이라 함)이란 당사의 본 약관에 동의하고 정해진 가입 절차에 따라 가입하여 정상적으로 멤버십 서비스를 이용할 수 있는 권한을 부여받은 고객을 의미합니다.\n" +
                "(2) 멤버십 포인트(이하 \"포인트\"라 함 )란 당사에서 상품 구매 또는 서비스 이용 시 적립/사용이 가능하도록 제공하는 포인트를 말합니다.\n" +
                "(3) 멤버십 서비스란 당사가 회원에게 제공하는 포인트 적립, 사용, 할인, 이벤트 참여 등의 전반적인 고객 서비스 프로그램을 말합니다.\n" +
                "\n" +
                "제3조 (멤버십 서비스 개요)\n" +
                "(1) 당사가 본 약관에 정해진 바에 따라 회원에게 제공하는 멤버십 서비스는 아래와 같습니다.\n" +
                "(a) 적립 서비스\n" +
                "회원은 당사에서 상품 구매 또는 서비스 이용, 이벤트 등에 의해 포인트를 적립 받을 수 있습니다. 단, 당사의 사정에 따라 지정된 일부 상품은 제외될 수 있습니다.\n" +
                "(b) 사용 서비스\n" +
                "회원은 적립된 포인트를 사용하여 당사에서 지정한 상품 구매 또는 서비스를 이용할 수 있습니다.\n" +
                "(c) 할인 서비스\n" +
                "회원은 당사에서 상품 구매 또는 서비스 이용 시 당사의 정책에 따라 상품 구매액의 일부를 포인트로 제공 또는 할인받을 수 있습니다.\n" +
                "(d) 기타 서비스\n" +
                "당사는 상기 각 호의 서비스 이외에도 추가적인 서비스를 개발하여 회원에게 제공할 수 있습니다.\n" +
                "(2) 당사의 멤버십 서비스를 원활하게 이용하기 위해서는 당사에서 정하는 별도의 약관에 대한 동의 및 개인정보 취급방침 (‘개인정보 제공 및 활용 동의’ 등) 등 추가 등록절차를 완료하여야 서비스를 원활히 받을 수 있습니다.\n" +
                "(3) 회원에게 제공되는 멤버십 서비스는 당사의 영업정책이나 사정에 따라 변경될 수 있으며, 이 경우 당사는 본 약관 제4조에서 정한 방법으로 이를 회원에게 공지합니다. 단, 폐업, 부도, 파산 등의 불가피한 사유로 인한 경우는 제외합니다.\n" +
                "\n" +
                "제4조 (약관 개정)\n" +
                "(1) 본 약관은 수시로 개정 가능하며 약관을 개정하고자 할 경우, 당사는 개정된 약관을 적용하고자 하는 날로부터 7일 이전에 약관이 개정된다는 사실과 개정된 내용 등을 아래에 규정된 방법 중 1가지 이상의 방법으로 회원에게 통지합니다. 다만, 회원에게 불리하게 약관 내용이 변경되는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 고지합니다.\n" +
                "(a) e-mail 통보\n" +
                "(b) 휴대전화 단문 메시지 (SMS)\n" +
                "(c) 기타 알림 메시지 (KAKAO TALK 등)\n" +
                "(2) 당사가 e-mail 통보 또는 기타 통보의 방법으로 본 약관이 개정된 사실 및 개정된 내용을 회원에게 고지하는 경우에는 회원이 제공한 e-mail 주소나 연락처 중 가장 최근에 제공된 곳으로 통보하며 이 경우, 당사가 적법한 통보를 완료한 것으로 봅니다.\n" +
                "(3) 본 규정에 따라 개정된 약관은 원칙적으로 그 효력 발생일로부터 장래를 향하여 유효합니다.\n" +
                "(4) 본 약관의 개정과 관련하여 이의가 있는 회원은 회원탈퇴를 할 수 있으며 개정된 약관의 효력 발생일까지 탈퇴하지 않거나 별도로 당사에 이의를 제기하지 않는 경우 변경된 약관에 동의한 것으로 간주합니다.\n" +
                "(5) 본 규정의 통지방법 및 통지의 효력은 본 약관의 각 조항에서 규정하는 개별적인 또는 전체적인 통지의 경우에 이를 준용합니다\n" +
                "\n" +
                "제2장 회원가입 및 탈퇴\n" +
                "\n" +
                "제5조 (회원가입)\n" +
                "(1) 회원으로 가입하고자 하는 고객은 당사에서 정한 회원가입 절차에 정해진 사항을 기재한 후 본 약관과 ‘개인정보 취급방침(‘개인정보 수집 제공 및 활용 동의’ 등)'에 동의함으로써 회원가입을 신청합니다. 단, 만 14세 미만의 경우, 가입이 불가합니다.\n" +
                "(2) 회원은 회원자격을 타인에게 양도하거나 대여 또는 담보의 목적으로 이용할 수 없습니다.\n" +
                "\n" +
                "제6조 (회원탈퇴 및 자격상실)\n" +
                "(1) 회원은 언제든지 서면, e-mail, 전화, 기타 당사가 정하는 방법으로 회원탈퇴를 요청할 수 있으며 당사는 본 조 제2항 경우를 제외하고는 회원의 요청에 따라 조속히 회원탈퇴에 필요한 제반 절차를 수행합니다. 단, 탈퇴 완료 후 이벤트 부정 이용 방지 등의 이유로 인해 14일 이내 재가입이 불가합니다.\n" +
                "(2) 포인트 사용 후 사용된 포인트의 적립 원천이 되는 구매 행위의 취소로 인해 마이너스(-) 포인트가 발생한 회원은 별도 당사의 승인이나 해당 포인트에 해당하는 금액을 변제하기 전까지는 탈퇴가 불가합니다.\n" +
                "(3) 회원이 다음 각 호에 해당하는 경우, 당사는 당해 회원에 대한 통보로써 회원자격을 상실시킬 수 있습니다. 단, (c)호의 경우에는 회원에 대한 통보 없이 자격이 상실됩니다.\n" +
                "(a) 회원가입 신청 시, 허위 내용을 등록하거나 타인의 명의를 도용한 경우\n" +
                "(b) 회원이 당사 또는 타 회원의 동의 없이 정당한 절차를 거치지 않고 부정적으로 포인트를 적립하거나 임의로 멤버십 제반 관련 서비스를 사용한 경우\n" +
                "(c) 회원이 사망한 경우\n" +
                "(d) 다른 회원의 당사 서비스 이용을 부당하게 방해하거나 그 정보를 도용하는 등 관련 법령을 위반하여 상거래 질서를 위협하는 경우\n" +
                "(e) 포인트 사용 후 사용된 포인트의 적립 원천이 되는 구매 행위의 취소를 상습적으로 (2회 이상) 반복해 당사에 손해를 끼치는 경우\n" +
                "(f) 법령 및 이 약관에서 금지하거나 공서양속에 반하는 행위를 하는 경우\n" +
                "(g) 기타 본 약관이 규정하는 회원의 의무를 위반하는 경우\n" +
                "(h) 포인트 부정 적립, 부정 사용 등 부정한 방법 또는 목적으로 이용한 경우\n" +
                "\"부정 적립\"이란 당사 회원이 실제로 상품을 구매하거나 서비스를 이용하지 않았음에도 당해 회원에게 포인트가 적립된 경우 (당사에서 상품을 구매하거나 서비스를 이용한 당사자의 동의가 있는 경우는 제외) 또는 실제 구입액 혹은 이용액 이상으로 포인트가 적립된 경우를 말합니다.\n" +
                "\"부정 사용\"이란 회원이 부정 적립한 포인트와 타 회원의 동의 없이 타 회원의 포인트를 임의로 사용한 경우를 말합니다.\n" +
                "*부정 적립된 포인트는 자격상실 통보와 동시에 소멸하고 이에 대하여 회원은 어떠한 권리를 주장할 수 없습니다. 또한, 부당 이익을 취한 부분 즉, 부정 적립된 포인트로 상품을 구매하거나 서비스를 이용한 경우 회원 본인 또는 부정 적립에 관여한 자는 관련 법령에 따라 민, 형사상의 책임을 부담할 수 있습니다.\n" +
                "*회원은 부정 사용의 사유가 자신의 고의 또는 과실에 기한 것이 아님을 소명할 수 있습니다. 이 경우 당사는 회원의 소명 내용을 심사하여 회원의 주장이 타당하다고 판단하는 경우, 정상적으로 포인트를 이용할 수 있도록 합니다.\n" +
                "(4)회원탈퇴 또는 자격상실은 회원탈퇴 요청일 또는 자격상실 통보일로부터 누적 포인트의 존재 여부와 상관없이 14일 이후에 확정됩니다. 단, 탈퇴를 요청한 회원 또는 자격을 상실한 회원이 포인트에 대한 소유권을 포기한다는 의사표시를 한 경우에는 회원탈퇴 요청일 또는 자격상실 통보일에 회원탈퇴 또는 자격상실이 확정됩니다. 단, 사망으로 인한 회원 자격상실의 경우에는 회원 사망일에 자격상실이 확정됩니다.\n" +
                "\n" +
                "제3장 \n" +
                "\n" +
                "제7조 (포인트 적립)\n" +
                "(1) 포인트는 회원이 당사에서 판매되는 상품이나 서비스의 구매하거나 당사에서 주최하는 이벤트, 추가 적립 서비스 등 마케팅 활동 등과 관련하여 획득한 포인트를 말합니다.\n" +
                "(2) 포인트는 회원의 상품 구매 또는 서비스 이용 금액에 비례하여 당사가 정하는 적립률에 따라 부여됩니다. 단, 상품 구매대금 또는 서비스 이용대금을 포인트로 환산할 때 소수점 이하의 포인트는 절사 됩니다.\n" +
                "(3) 포인트는 회원의 상품 구매 또는 서비스 이용 당시에 적립하는 것이 원칙이나, 그 당시에 적립하지 못했을 경우에는 상품을 구매하거나 서비스를 이용한 날부터 10일 이내에 적립 가능합니다. 단, 적립 시 당사에 유효한 영수증을 함께 제시하여야 합니다.\n" +
                "(4) 포인트의 적립과 관련하여 발생하는 제세공과금은 회원이 부담합니다. \n" +
                "\n" +
                "제8조 (포인트 사용)\n" +
                "(1) 포인트를 사용하기 위해서는 회원가입을 통해 회원의 지위를 취득해야 합니다. 포인트 사용 시에는 본인 인증 또는 휴대전화 번호 인증을 해야 합니다. \n" +
                "(2) 14세 미만의 미성년자 회원이 포인트를 사용하는 경우, 최초 회원가입 시 법정대리인의 동의를 통해 회원가입을 완료한 것으로 포인트 사용에 대한 법정대리인의 동의도 획득한 것으로 간주합니다.\n" +
                "(3) 회원은 포인트를 타인에게 양도하거나 대여 또는 담보의 목적으로 이용할 수 없습니다. 단, 당사가 인정하는 적법한 절차를 따른 경우는 예외로 합니다.\n" +
                "(4) 포인트 사용 후 사용된 포인트의 적립 원천이 되는 구매 행위의 취소로 인해 잔여 포인트가 마이너스(-)인 경우, 잔여 가용 포인트가 0 포인트가 되기 전까지 적립되는 포인트는 사용이 불가합니다.\n" +
                "(5) 회원의 고객정보가 등록되어 있지 않은 경우, 포인트 사용이 제한될 수도 있습니다.\n" +
                "\n" +
                "제9조 (포인트 정정, 취소 및 소멸)\n" +
                "(1) 포인트의 적립에 오류가 있을 경우, 회원은 오류 발생 시점부터 30일 이내에 당사에 정정 신청을 하여야 하며, 당사는 회원의 정정 요청일부터 30일 이내에 정정할 수 있습니다. 단, 회원은 이를 증명할 수 있는 유효한 영수증 등의 자료를 제시해야 합니다.\n" +
                "(2) 당사는 회원에게 영수증 또는 기타의 방법으로 고지된 포인트라 할지라도 정산과정에서 미결제 금액이 발생하거나 지급 불능 상태가 발생하였을 경우, 이미 부여된 포인트를 취소할 수 있습니다. 단, 적립된 포인트를 취소할 경우 당사는 회원에 대한 서비스 차원에서 당사 소정의 포인트를 보상 포인트로 해당 회원에게 제공할 수 있습니다.\n" +
                "(4) 포인트의 유효기간은 그 적립일부터 24개월이며, 포인트를 적립한 후 사용하지 않고 유효기간이 경과 된 포인트는 유효기간 경과 분에 한해 월 단위 선입선출방식에 의하여 자동 소멸합니다. \n" +
                "(5) 당사는 소멸 포인트 발생 시, 소멸 포인트가 발생한 회원 모두에게 본 약관 제4조 제1항에 의한 방법으로 통지합니다. 단, 회원이 잘못된 정보를 제공하여 안내정보를 수신하지 못한 책임은 회원 본인에게 있습니다.\n" +
                "\n" +
                "제10조 (회원탈퇴 및 자격상실의 포인트처리)\n" +
                "(1) 본 약관 제6조 제1항에 정해진 방법으로 회원탈퇴를 하고자 하는 회원은 회원탈퇴 요청일 현재까지 누적 포인트가 존재하는 경우 잔여 포인트를 해소 기간(회원탈퇴 요청일로부터 30일 후)까지 본 약관이 정하는 바에 따라 사용해야 하며, 해소 기간까지 사용하지 않은 누적 포인트는 자동으로 소멸합니다.\n" +
                "(2) 본 약관 제6조 제3항에 정해진 바에 의하여 자격상실 통보를 받은 회원은 통보일 현재까지 누적 포인트가 존재하는 경우 잔여 포인트를 해소 기간(자격상실 통보일로부터 30일 후)까지 본 약관이 정하는 바에 따라 사용해야 하며, 해소 기간까지 사용하지 않은 누적 포인트는 자동으로 소멸합니다.\n" +
                "(3) 본 조 제2항의 규정에도 불구하고 본 약관 제6조 제3항 (b)호에 해당하여 당사로부터 자격상실 통보를 받은 회원의 누적 포인트 중 부정 적립된 포인트는 자격상실 통보와 동시에 소멸하고, 이에 대하여 회원은 어떠한 권리도 주장할 수 없습니다.\n" +
                "\n" +
                "제4장 기타\n" +
                "\n" +
                "제11조 (멤버십 서비스의 종료)\n" +
                "(1) 당사는 영업부문의 폐지나 통합, 영업양도나 합병, 분할, 경영정책의 변경 등 부득이한 사정이 있는 경우 본 약관에 의한 멤버십 서비스를 일방적으로 종료 또는 폐쇄할 수 있습니다. \n" +
                "(2) 당사는 멤버십 서비스를 종료 또는 폐쇄하고자 하는 경우 중단 또는 폐쇄 시점부터 최소 1개월 이전에 본 약관 제4조 통보 방법에 의거하여 회원에게 통보합니다. 멤버십 서비스 종료일 이후 회원은 당사에서 포인트 적립 및 기타 멤버십 서비스 혜택을 받지 못하며, 통지일 시점에서 이미 적립된 포인트는 당사가 별도 지정하는 날(이하 \"포인트 소멸일\")까지 본 약관과 당사가 지정하는 바에 따라 사용하여야 하며, 포인트 소멸일 이후 미사용 잔여분은 소멸합니다\n" +
                "\n" +
                "제12조 (본 약관에서 정하지 않은 사항)\n" +
                "(1) 본 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 관계 법령 및 상관례에 따릅니다.\n" +
                "\n" +
                "제13조 (면책 조항)\n" +
                "(1) 당사는 천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 멤버십 서비스 제공에 관한 책임이 면제됩니다.\n" +
                "(2) 당사는 회원의 귀책 사유로 인한 멤버십 서비스 이용의 장애에 대하여 책임을 지지 않습니다.\n" +
                "(3) 당사는 회원이 멤버십 서비스를 이용하여 기대하는 수익을 상실한 것이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않습니다.\n" +
                "(4) 당사는 회원이 멤버십 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.\n" +
                "(5) 당사는 멤버십 서비스 이용과 관련하여 가입자에게 발생한 손해 가운데 회원의 고의, 과실에 의한 손해에 대하여 책임을 지지 않습니다.\n" +
                "\n" +
                "제14조 (준거법 및 합의 관할)\n" +
                "(1) 본 약관에서 정하지 않은 사항과 본 약관의 해석에 관하여는 대한민국 관련 법령 및 상관례에 따릅니다.\n" +
                "(2) 본 약관에 의한 서비스 이용과 관련한 제반 분쟁 및 소송은 회원의 주소를 관할하는 법원에 의하고, 주소가 없는 경우에는 거주하는 곳을 관할하는 지방법원의 전속관할로 합니다. 다만, 제소 당시 이용자의 주소 또는 거주하는 곳이 분명하지 않거나 외국 거주자의 경우에는 민사소송법상의 관할법원에 제기합니다.\n");

        terms2.append("정보통신망법 규정에 따라 올바스켓에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.\n" +
                "\n" +
                "1. 수집하는 개인정보\n" +
                "고객은 회원가입을 하지 않아도 상품 구매 및 할인 서비스를 회원과 동일하게 이용할 수 있습니다. 고객이 멤버십 포인트 서비스를 이용하기 위해 회원가입을 할 경우, 올바스켓은 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.\n" +
                "회원가입 시점에 올바스켓이 고객으로부터 수집하는 개인정보는 아래와 같습니다.\n" +
                "- 회원가입 시에 ‘이름, 성별, 휴대전화번호, 주소’를 필수항목으로 수집합니다. 그리고 선택항목으로 전화번호, 이메일 주소를 수집합니다.\n" +
                "서비스 이용 과정에서 고객으로부터 수집하는 개인정보는 아래와 같습니다.\n" +
                "이벤트 응모 및 경품 신청 과정에서 해당 서비스의 고객에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 고객에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관 기간’에 대해 안내해 드리고 동의를 받습니다.\n" +
                "\n" +
                "2. 수집한 개인정보의 이용\n" +
                "올바스켓의 회원 관리, 서비스 개발・제공 및 향상 등 아래의 목적으로만 개인정보를 이용합니다.\n" +
                "- 회원가입 의사의 확인, 고객 식별, 회원탈퇴 의사의 확인 등 회원 관리를 위하여 개인정보를 이용합니다.\n" +
                "- 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다.\n" +
                "- 법령 및 올바스켓 멤버십 서비스 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 회원 도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁 조정을 위한 기록 보존, 민원처리 등 고객 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.\n" +
                "- 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.\n" +
                "- 서비스 이용기록과 방문 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 등에 개인정보를 이용합니다.\n" +
                "- 보안, 프라이버시, 안전 측면에서 고객이 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.\n" +
                "\n" +
                "3. 개인정보의 파기\n" +
                "올바스켓은 원칙적으로 고객의 개인정보를 회원탈퇴 시 즉각 파기하고 있습니다.\n" +
                "단, 고객에게 개인정보 보관 기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다.\n");

        termsTV1.setText(terms1);
        termsTV2.setText(terms2);

        expand1.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (scrollView1.getVisibility() == View.GONE) {
                    expand1.setImageResource(R.drawable.ic_arrow_up);
                    scrollView1.setVisibility(View.VISIBLE);
                } else {
                    expand1.setImageResource(R.drawable.ic_arrow_down);
                    scrollView1.setVisibility(View.GONE);
                }
            }
        });

        expand2.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (scrollView2.getVisibility() == View.GONE) {
                    expand2.setImageResource(R.drawable.ic_arrow_up);
                    scrollView2.setVisibility(View.VISIBLE);
                } else {
                    expand2.setImageResource(R.drawable.ic_arrow_down);
                    scrollView2.setVisibility(View.GONE);
                }
            }
        });

        checkBoxAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkCheckBox(buttonView);
        });

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkCheckBox(buttonView);
        });

        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkCheckBox(buttonView);
        });

        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkCheckBox(buttonView);
        });

        next.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (getParentFragment() != null) {
                    ((DialogMemberSignup) getParentFragment()).mNext.callOnClick();
                }
            }
        });

        return view;
    }

    public void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(300);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(300);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    public void checkCheckBox(View view) {
        if (checkBox1.isChecked() && checkBox2.isChecked() && next.getVisibility() == View.INVISIBLE || checkBox1.isChecked() && checkBox2.isChecked() && next.getVisibility() == View.GONE) {
            slideUp(next);
        } else if ((!checkBox1.isChecked() && next.getVisibility() == View.VISIBLE) || (!checkBox2.isChecked() && next.getVisibility() == View.VISIBLE)) {
            slideDown(next);
        }

        if (checkBoxAll.isChecked() && view.getId() == R.id.checkall) {
            checkBox1.setChecked(true);
            checkBox2.setChecked(true);
            checkBox3.setChecked(true);
        } else if (!checkBoxAll.isChecked() && view.getId() == R.id.checkall) {
            if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()) {
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
            }
        } else if (!checkBoxAll.isChecked()) {
            if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()) {
                checkBoxAll.setChecked(true);
            }
        } else if (checkBoxAll.isChecked() && !checkBox1.isChecked() || !checkBox2.isChecked()) {
            checkBoxAll.setChecked(false);
        } else if (checkBoxAll.isChecked() && !checkBox1.isChecked() || !checkBox3.isChecked()) {
            checkBoxAll.setChecked(false);
        }
    }

}
