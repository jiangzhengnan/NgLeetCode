package com.ng.ngleetcode.ui.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ng.code.util.CodeBean;
import com.ng.code.util.ProblemAndroidUtil;
import com.ng.ngleetcode.databinding.FragmentHomeBinding;
import com.ng.ngleetcode.utils.UIUtil;
import com.ng.ngleetcode.view.code.CodeView;
import com.ng.ngleetcode.view.code.Language;
import com.ng.ngleetcode.view.code.Theme;


public class HomeFragment extends Fragment implements CodeView.OnHighlightListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.codeContent.setRadius(UIUtil.dp2px(getContext(), 5));
        binding.codeView.setOnHighlightListener(this)
                .setOnHighlightListener(this)
                .setTheme(Theme.ANDROIDSTUDIO)
                .setLanguage(Language.JAVA)
                .setWrapLine(false)
                .setFontSize(8)
                .setZoomEnabled(true)
                .setShowLineNumber(false)
                .setStartLineNumber(0)
                .apply();

        showRandomProblem();
        return root;
    }

    private void showRandomProblem() {
        CodeBean codeBean = ProblemAndroidUtil.getRandomProblemJavaContent(getContext());
        binding.codeView.setCode(codeBean.content).apply();
        setTitle(codeBean.title);
    }

    public void refreshData() {
        showRandomProblem();
        showAnim();
    }

    private void showAnim() {
        PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat(
                "alpha", 1.0f, 0f, 1.0f);
        ValueAnimator valueAnimator = ObjectAnimator
                .ofPropertyValuesHolder(binding.tvTitle, pvhAlpha);
        valueAnimator.start();
        startAnim(binding.codeView);
    }

    @SuppressLint("Recycle")
    private void startAnim(View target) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(target, "rotation", 0, -360f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(target, "scaleX", 1f, 0.1f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(target, "scaleY", 1f, 0.1f, 1f);
        target.setPivotX(target.getMeasuredWidth());
        target.setPivotY(target.getMeasuredHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2, animator3);
        animatorSet.start();
    }


    public void refreshData(CodeBean codeBean) {
        codeBean.content = ProblemAndroidUtil.readAssets(getContext(), codeBean.contentPath);
        binding.codeView.setCode(codeBean.content).apply();
        setTitle(codeBean.title);
        showAnim();
    }

    public void setTitle(String title) {
        binding.tvTitle.setText(title.replace(".java", ""));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStartCodeHighlight() {

    }

    @Override
    public void onFinishCodeHighlight() {

    }

    @Override
    public void onLanguageDetected(Language language, int relevance) {

    }

    @Override
    public void onFontSizeChanged(int sizeInPx) {

    }

    @Override
    public void onLineClicked(int lineNumber, String content) {

    }
}