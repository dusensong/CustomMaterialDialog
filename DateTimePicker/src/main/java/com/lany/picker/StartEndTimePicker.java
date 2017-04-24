package com.lany.picker;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class StartEndTimePicker extends FrameLayout {

    private static final boolean DEFAULT_ENABLED_STATE = true;

    private final NumberPicker mStartHourSpinner;
    private final NumberPicker mStartMinuteSpinner;
    private final NumberPicker mEndHourSpinner;
    private final NumberPicker mEndMinuteSpinner;

    private final EditText mStartHourSpinnerInput;
    private final EditText mStartMinuteSpinnerInput;
    private final EditText mEndHourSpinnerInput;
    private final EditText mEndMinuteSpinnerInput;

    private boolean mIsEnabled = DEFAULT_ENABLED_STATE;

    private OnTimeChangedListener mOnTimeChangedListener;

    private Calendar mTempCalendar;

    private Locale mCurrentLocale;

    public interface OnTimeChangedListener {
        void onTimeChanged(View view, int startHour, int startMinute, int endHour, int endMinute);
    }

    public StartEndTimePicker(Context context) {
        this(context, null);
    }

    public StartEndTimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.timePickerStyle);
    }

    public StartEndTimePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCurrentLocale(Locale.getDefault());
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.start_end_time_picker_holo, this, true);

        // start hour
        mStartHourSpinner = (NumberPicker) findViewById(R.id.start_hour);
        mStartHourSpinner.setMinValue(0);
        mStartHourSpinner.setMaxValue(23);
        mStartHourSpinner.setOnLongPressUpdateInterval(100);
        mStartHourSpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
        mStartHourSpinner
                .setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    public void onValueChange(NumberPicker spinner, int oldVal,
                                              int newVal) {
                        updateInputState();
                        onTimeChanged();
                    }
                });
        mStartHourSpinnerInput = (EditText) mStartHourSpinner
                .findViewById(R.id.np__numberpicker_input);
        mStartHourSpinnerInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        // end hour
        mEndHourSpinner = (NumberPicker) findViewById(R.id.end_hour);
        mEndHourSpinner.setMinValue(0);
        mEndHourSpinner.setMaxValue(23);
        mEndHourSpinner.setOnLongPressUpdateInterval(100);
        mEndHourSpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
        mEndHourSpinner
                .setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    public void onValueChange(NumberPicker spinner, int oldVal,
                                              int newVal) {
                        updateInputState();
                        onTimeChanged();
                    }
                });
        mEndHourSpinnerInput = (EditText) mStartHourSpinner
                .findViewById(R.id.np__numberpicker_input);
        mEndHourSpinnerInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        // start minute
        mStartMinuteSpinner = (NumberPicker) findViewById(R.id.start_minute);
        mStartMinuteSpinner.setMinValue(0);
        mStartMinuteSpinner.setMaxValue(59);
        mStartMinuteSpinner.setOnLongPressUpdateInterval(100);
        mStartMinuteSpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
        mStartMinuteSpinner
                .setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    public void onValueChange(NumberPicker spinner, int oldVal,
                                              int newVal) {
                        updateInputState();
                        onTimeChanged();
                    }
                });
        mStartMinuteSpinnerInput = (EditText) mStartMinuteSpinner
                .findViewById(R.id.np__numberpicker_input);
        mStartMinuteSpinnerInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        // end minute
        mEndMinuteSpinner = (NumberPicker) findViewById(R.id.end_minute);
        mEndMinuteSpinner.setMinValue(0);
        mEndMinuteSpinner.setMaxValue(59);
        mEndMinuteSpinner.setOnLongPressUpdateInterval(100);
        mEndMinuteSpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
        mEndMinuteSpinner
                .setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    public void onValueChange(NumberPicker spinner, int oldVal,
                                              int newVal) {
                        updateInputState();
                        onTimeChanged();
                    }
                });
        mEndMinuteSpinnerInput = (EditText) mStartMinuteSpinner
                .findViewById(R.id.np__numberpicker_input);
        mEndMinuteSpinnerInput.setImeOptions(EditorInfo.IME_ACTION_NEXT);

//        // update controls to initial state
//        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
//
        // 初始化开始时间和结束时间为当前时间
        setCurrentStartHour(mTempCalendar.get(Calendar.HOUR_OF_DAY));
        setCurrentStartMinute(mTempCalendar.get(Calendar.MINUTE));
        setCurrentEndHour(mTempCalendar.get(Calendar.HOUR_OF_DAY));
        setCurrentEndMinute(mTempCalendar.get(Calendar.MINUTE));

        if (!isEnabled()) {
            setEnabled(false);
        }

        // If not explicitly specified this view is important for accessibility.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && getImportantForAccessibility() == IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
            setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
        }
    }

    public void setSelectionDivider(Drawable selectionDivider) {
        mStartHourSpinner.setSelectionDivider(selectionDivider);
        mStartMinuteSpinner.setSelectionDivider(selectionDivider);
        mEndHourSpinner.setSelectionDivider(selectionDivider);
        mEndMinuteSpinner.setSelectionDivider(selectionDivider);
    }

    public void setSelectionDividerHeight(int selectionDividerHeight) {
        mStartHourSpinner.setSelectionDividerHeight(selectionDividerHeight);
        mStartMinuteSpinner.setSelectionDividerHeight(selectionDividerHeight);
        mEndHourSpinner.setSelectionDividerHeight(selectionDividerHeight);
        mEndMinuteSpinner.setSelectionDividerHeight(selectionDividerHeight);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (mIsEnabled == enabled) {
            return;
        }
        super.setEnabled(enabled);
        mStartHourSpinner.setEnabled(enabled);
        mStartMinuteSpinner.setEnabled(enabled);
        mEndHourSpinner.setEnabled(enabled);
        mEndMinuteSpinner.setEnabled(enabled);
        mIsEnabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return mIsEnabled;
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setCurrentLocale(newConfig.locale);
    }

    /**
     * Sets the current locale.
     *
     * @param locale The current locale.
     */
    private void setCurrentLocale(Locale locale) {
        if (locale.equals(mCurrentLocale)) {
            return;
        }
        mCurrentLocale = locale;
        mTempCalendar = Calendar.getInstance(locale);
    }

    /**
     * Used to save / restore state of time picker
     */
    private static class SavedState extends BaseSavedState {

        private final int mStartHour;
        private final int mStartMinute;
        private final int mEndHour;
        private final int mEndMinute;

        private SavedState(Parcelable superState, int startHour, int startMinute,
                           int endHour, int endMinute) {
            super(superState);
            mStartHour = startHour;
            mStartMinute = startMinute;
            mEndHour = endHour;
            mEndMinute = endMinute;
        }

        private SavedState(Parcel in) {
            super(in);
            mStartHour = in.readInt();
            mStartMinute = in.readInt();
            mEndHour = in.readInt();
            mEndMinute = in.readInt();
        }

        public int getStartHour() {
            return mStartHour;
        }

        public int getStartMinute() {
            return mStartMinute;
        }

        public int getEndHour() {
            return mEndHour;
        }

        public int getEndMinute() {
            return mEndMinute;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(mStartHour);
            dest.writeInt(mStartMinute);
            dest.writeInt(mEndHour);
            dest.writeInt(mEndMinute);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, getCurrentStartHour(), getCurrentStartMinute(),
                getCurrentStartHour(), getCurrentStartMinute());
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setCurrentStartHour(ss.getStartHour());
        setCurrentStartMinute(ss.getStartMinute());
        setCurrentEndHour(ss.getEndHour());
        setCurrentEndMinute(ss.getEndMinute());
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        mOnTimeChangedListener = onTimeChangedListener;
    }

    /**
     * @return The current start hour in the range (0-23).
     */
    public int getCurrentStartHour() {
        return mStartHourSpinner.getValue();
    }

    /**
     * Set the current start hour.
     */
    public void setCurrentStartHour(int currentHour) {
        // why was Integer used in the first place?
        if (currentHour == getCurrentStartHour()) {
            return;
        }
        mStartHourSpinner.setValue(currentHour);
        onTimeChanged();
    }

    /**
     * @return The current end hour in the range (0-23).
     */
    public int getCurrentEndHour() {
        return mEndHourSpinner.getValue();
    }

    /**
     * Set the current end hour.
     */
    public void setCurrentEndHour(int currentHour) {
        // why was Integer used in the first place?
        if (currentHour == getCurrentEndHour()) {
            return;
        }
        mEndHourSpinner.setValue(currentHour);
        onTimeChanged();
    }


    /**
     * @return The current start minute.
     */
    public int getCurrentStartMinute() {
        return mStartMinuteSpinner.getValue();
    }

    /**
     * Set the current start minute (0-59).
     */
    public void setCurrentStartMinute(int currentMinute) {
        if (currentMinute == getCurrentStartMinute()) {
            return;
        }
        mStartMinuteSpinner.setValue(currentMinute);
        onTimeChanged();
    }

    /**
     * @return The current end minute.
     */
    public int getCurrentEndMinute() {
        return mEndMinuteSpinner.getValue();
    }

    /**
     * Set the current end minute (0-59).
     */
    public void setCurrentEndMinute(int currentMinute) {
        if (currentMinute == getCurrentEndMinute()) {
            return;
        }
        mEndMinuteSpinner.setValue(currentMinute);
        onTimeChanged();
    }

    @Override
    public int getBaseline() {
        return mStartHourSpinner.getBaseline();
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        onPopulateAccessibilityEvent(event);
        return true;
    }

    @Override
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
        // TODO: 2017/4/24 这里只考虑了开始时间，应该加入结束时间
        int flags = DateUtils.FORMAT_SHOW_TIME;
        flags |= DateUtils.FORMAT_24HOUR;
        mTempCalendar.set(Calendar.HOUR_OF_DAY, getCurrentStartHour());
        mTempCalendar.set(Calendar.MINUTE, getCurrentStartMinute());
        String selectedDateUtterance = DateUtils.formatDateTime(getContext(),
                mTempCalendar.getTimeInMillis(), flags);
        event.getText().add(selectedDateUtterance);
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(StartEndTimePicker.class.getName());
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(StartEndTimePicker.class.getName());
    }

    private void updateHourControl() {
        mStartHourSpinner.setMinValue(0);
        mStartHourSpinner.setMaxValue(23);
        mStartHourSpinner.setFormatter(NumberPicker.getTwoDigitFormatter());
    }

    private void onTimeChanged() {
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
        if (mOnTimeChangedListener != null) {
            mOnTimeChangedListener.onTimeChanged(this, getCurrentStartHour(),
                    getCurrentStartMinute(), getCurrentEndHour(), getCurrentEndMinute());
        }
    }

    private void updateInputState() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            if (inputMethodManager.isActive(mStartHourSpinnerInput)) {
                mStartHourSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(mStartMinuteSpinnerInput)) {
                mStartMinuteSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(mEndHourSpinner)) {
                mEndHourSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(mEndMinuteSpinner)) {
                mEndMinuteSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }
}
