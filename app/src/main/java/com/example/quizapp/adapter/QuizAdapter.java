package com.example.quizapp.adapter;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.quizapp.R;

import com.example.quizapp.databinding.ItemQuestionBinding;
import com.example.quizapp.interfaces.OnAnswerClick;
import com.example.quizapp.interfaces.OnItemClickListener;
import com.example.quizapp.models.ResultModel;
import com.example.quizapp.models.ScoreModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static android.content.ContentValues.TAG;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<ResultModel> listQues = new ArrayList<>();
    OnAnswerClick listener;


    public void setListener(OnAnswerClick listener) {
        this.listener = listener;
    }

    public void setListQues(List<ResultModel> listQues) {
        this.listQues = listQues;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemQuestionBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listQues.get(position));

    }

    @Override
    public int getItemCount() {
        return listQues.size();
    }

    public void addData(List<ResultModel> resultModels) {
        listQues.addAll(resultModels);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements OnAnswerClick {
        public static final int CORRECT_ANSWER = 1;
        public static final int INCORRECT_ANSWER = 2;
        private static final int WRONG_ANSWER = 3;
        private ScoreModel scoreModel;
        ResultModel model;

        private ItemQuestionBinding questionBinding;

        public ViewHolder(@NonNull ItemQuestionBinding binding) {
            super(binding.getRoot());
            questionBinding = binding;
            binding.setListener(this);
        }

        public void onBind(ResultModel resultModel) {
            //this.model=resultModel;
            //setClickable(!model.getSkipClicked().getValue()); //Скиптики
            // observeSkipClick(); // Скиптики
            questionBinding.btnone.setBackgroundResource(R.color.White);
            questionBinding.btntwo.setBackgroundResource(R.color.White);
            questionBinding.btnthree.setBackgroundResource(R.color.White);
            questionBinding.btnfour.setBackgroundResource(R.color.White);
            questionBinding.btnTrue.setBackgroundResource(R.color.White);
            questionBinding.btnFalse.setBackgroundResource(R.color.White);

            if (resultModel.isChoice()){
                switch (resultModel.getUserChoice()){
                    case 0:
                        questionBinding.btnone.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                    case 1:
                        questionBinding.btntwo.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                    case 2:
                        questionBinding.btnthree.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                    case 3:
                        questionBinding.btnfour.setBackgroundResource(R.drawable.back_incoret_answer);
                        break;
                }
                showCorrectButton();
            }


            questionBinding.tvQuestion.setText(resultModel.getQuestion());
            Log.e("ololo", "onBind: " + resultModel.isChoice() + "  " + resultModel.getIncorrectAnswers().size());

            if (!resultModel.isBind()) {
                resultModel.getIncorrectAnswers().add(resultModel.getCorrectAnswer());
                Collections.shuffle(resultModel.getIncorrectAnswers());
                setClickable(true);
                resultModel.setBind(true);
            } else setClickable(false);


            if (resultModel.getIncorrectAnswers().size() == 2) {
                questionBinding.layoutTrueFalse.setVisibility(View.VISIBLE);
                questionBinding.layoutVariant.setVisibility(View.GONE);
                questionBinding.btnTrue.setText(resultModel.getIncorrectAnswers().get(0));
                questionBinding.btnFalse.setText(resultModel.getIncorrectAnswers().get(1));

            } else {
                questionBinding.layoutVariant.setVisibility(View.VISIBLE);
                questionBinding.layoutTrueFalse.setVisibility(View.GONE);
                questionBinding.btnone.setText(resultModel.getIncorrectAnswers().get(0));
                questionBinding.btntwo.setText(resultModel.getIncorrectAnswers().get(1));
                questionBinding.btnthree.setText(resultModel.getIncorrectAnswers().get(2));
                questionBinding.btnfour.setText(resultModel.getIncorrectAnswers().get(3));
            }
            questionBinding.tvQuestion.setText(resultModel.getQuestion());

            questionBinding.setModel(resultModel);
        }

        private void showCorrectButton(){
            String corAnswer = questionBinding.getModel().getCorrectAnswer();
            int positionCorAnswer = 0;
            for (int i = 0; i < questionBinding.getModel().getIncorrectAnswers().size(); i++) {
                if (corAnswer.equals(questionBinding.getModel().getIncorrectAnswers().get(i)));
                positionCorAnswer = i;
            }
            switch (positionCorAnswer) {
                case 0:
                    questionBinding.btnone.setBackgroundResource(R.drawable.back_answer);
                    break;
                case 1:
                    questionBinding.btntwo.setBackgroundResource(R.drawable.back_answer);
                    break;
                case 2:
                    questionBinding.btnthree.setBackgroundResource(R.drawable.back_answer);
                    break;
                case 3:
                    questionBinding.btnfour.setBackgroundResource(R.drawable.back_answer);
                    break;
            }
        }
        private void setClickable(boolean b) {
            questionBinding.btnone.setClickable(b);
            questionBinding.btntwo.setClickable(b);
            questionBinding.btnthree.setClickable(b);
            questionBinding.btnfour.setClickable(b);
            questionBinding.btnTrue.setClickable(b);
            questionBinding.btnFalse.setClickable(b);
        }

        @Override
        public void onAnswer(View view, int positionQuestion, int positionAnswer) {
            questionBinding.getModel().setChoice(true);
            questionBinding.getModel().setUserChoice(positionAnswer);
            setClickable(false);
            Button button = (Button) view;
            int position = 0;
            ResultModel quizModel = Objects.requireNonNull(listQues.get(getAdapterPosition()));
            String userAnswer = quizModel.getIncorrectAnswers().get(positionAnswer);
            if (userAnswer.equals(quizModel.getCorrectAnswer())) {
                if (getAdapterPosition() >= listQues.size()-1) {
                    button.setBackgroundResource(R.drawable.back_answer);
                    correctAnswer(true);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    position = CORRECT_ANSWER;
                    listener.correctAnswer(true);

                    Log.e(TAG, "ansverOk: " + quizModel.getCorrectAnswer());
                } else {
                    button.setBackgroundResource(R.drawable.back_answer);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    listener.correctAnswer(true);

                }
            } else {
                if (getAdapterPosition() >= listQues.size()-1) {
                    button.setBackgroundResource(R.drawable.back_incoret_answer);
                    correctAnswer(false);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    position = INCORRECT_ANSWER;
                    listener.correctAnswer(false);

                } else {
                    button.setBackgroundResource(R.drawable.back_incoret_answer);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(5)
                            .playOn(view);
                    position = WRONG_ANSWER;
                    listener.correctAnswer(false);

                }
                Log.e(TAG, "ansverOk: " + quizModel.getIncorrectAnswers());
            }
            new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            }.start();
        }

        @Override
        public void correctAnswer(boolean b) {
        }




    }
}
