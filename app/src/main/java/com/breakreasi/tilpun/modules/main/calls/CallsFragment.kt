package com.breakreasi.tilpun.modules.main.calls

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.breakreasi.tilpun.R
import com.breakreasi.tilpun.databinding.FragmentCallsBinding
import com.breakreasi.tilpun.modules.dialer.DialerActivity

class CallsFragment : Fragment(), MenuProvider {

    private var _binding: FragmentCallsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val callsViewModel =
            ViewModelProvider(this).get(CallsViewModel::class.java)

        _binding = FragmentCallsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        callsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Attach MenuProvider
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_fragment_calls, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_add -> {
                val intent = Intent(requireContext(), DialerActivity::class.java)
                startActivity(intent)
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}