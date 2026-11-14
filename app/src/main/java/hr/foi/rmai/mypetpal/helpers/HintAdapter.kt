package hr.foi.rmai.mypetpal.helpers

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import hr.foi.rmai.mypetpal.R

class HintAdapter(context: Context, resource: Int, private val items: List<String>) :
    ArrayAdapter<String>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view as TextView
        val color = if (position == 0) R.color.grey_outline else R.color.black
        textView.setTextColor(ContextCompat.getColor(context, color))
        return view
    }

    override fun isEnabled(position: Int): Boolean {
        // Disable the first item (hint text)
        return position != 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view as TextView
        val color = if (position == 0) R.color.grey_outline else R.color.black
        textView.setTextColor(ContextCompat.getColor(context, color))
        return view
    }
}