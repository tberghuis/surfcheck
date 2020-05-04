package site.thomasberghuis.surfcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment




class BeachFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_beach, container, false)
        val textView: TextView = root.findViewById(R.id.text_beach)
        textView.text = "beach view"
        return root
    }
}
