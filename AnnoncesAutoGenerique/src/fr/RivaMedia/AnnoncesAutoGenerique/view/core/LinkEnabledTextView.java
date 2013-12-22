package fr.RivaMedia.AnnoncesAutoGenerique.view.core;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LinkEnabledTextView
{

	TextView _textview;
	// The String Containing the Text that we have to gather links from private SpannableString linkableText;
	// Populating and gathering all the links that are present in the Text
	private ArrayList<Hyperlink> listOfLinks; 

	// A Listener Class for generally sending the Clicks to the one which requires it
	TextLinkClickListener mListener;

	// Pattern for gathering @usernames from the Text
	Pattern screenNamePattern = Pattern.compile("(@[a-zA-Z0-9_]+)");

	// Pattern for gathering #hasttags from the Text
	Pattern hashTagsPattern = Pattern.compile("(#[a-zA-Z_0-9_àáâãäåçèéêëìíîïðòóôõöùúûüýÿ#\']+|(#.*#))");

	// Pattern for gathering http:// links from the Text
	Pattern hyperLinksPattern = Pattern.compile("([Hh][tT][tT][pP][sS]?:\\/\\/[^ ,'\">\\]\\)]*[^\\. ,'\">\\]\\)])");

	public LinkEnabledTextView(Context context, TextView textview, String texte, TextLinkClickListener listener)
	{
		this._textview = textview;
		mListener = listener;
		listOfLinks = new ArrayList<Hyperlink>();
		gatherLinksForText(texte);

		_textview.setTextColor(Color.BLACK);
		_textview.setLinkTextColor(Color.GRAY);

		if(listener != null)
			_textview.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public void gatherLinksForText(String text)
	{
		text = text+" ";
		SpannableString linkableText = new SpannableString(text);
		/*
		 *  gatherLinks basically collects the Links depending upon the Pattern that we supply
		 *  and add the links to the ArrayList of the links
		 */
		gatherLinks(linkableText, screenNamePattern);
		gatherLinks(linkableText, hashTagsPattern);
		gatherLinks(linkableText, hyperLinksPattern);

		Log.d("listOfLinks","nb:"+listOfLinks.size());

		for(int i = 0; i< listOfLinks.size(); i++)
		{
			Hyperlink linkSpec = listOfLinks.get(i);
			Log.d("listOfLinks",linkSpec.textSpan.toString());
			/*
			 * this process here makes the Clickable Links from the text
			 */
			linkableText.setSpan(linkSpec.span, linkSpec.start, linkSpec.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		/*
		 * sets the text for the TextView with enabled links
		 */
		this._textview.setText(linkableText);
	}

	/*
	 * The Method mainly performs the Regex Comparison for the Pattern and adds them to
	 * listOfLinks array list
	 */

	private final void gatherLinks(Spannable s, Pattern pattern){
		// Matcher matching the pattern
		Matcher m = pattern.matcher(s);

		while (m.find())
		{
			int start = m.start();
			int end = m.end();

			/*
			 *  Hyperlink is basically used like a structure for storing the information about
			 *  where the link was found.
			 */
			Hyperlink spec = new Hyperlink();

			spec.textSpan = s.subSequence(start, end);
			spec.span = new InternalURLSpan(spec.textSpan.toString());
			spec.start = start;
			spec.end = end;

			this.listOfLinks.add(spec);
		}
	}

	/*
	 * This is class which gives us the clicks on the links which we then can use.
	 */
	public class InternalURLSpan extends ClickableSpan
	{
		private String clickedSpan;

		public InternalURLSpan (String clickedString)
		{
			clickedSpan = clickedString;
		}

		@Override
		public void onClick(View textView)
		{
			Log.d("listOfLinks","onclick");
			mListener.onTextLinkClick(textView, clickedSpan);
		}
	}

	/*
	 * Class for storing the information about the Link Location
	 */

	class Hyperlink
	{
		CharSequence textSpan;
		InternalURLSpan span;
		int start;
		int end;
	}
}