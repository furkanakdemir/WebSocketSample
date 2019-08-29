package net.furkanakdemir.websocketsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_message_list.emptyTextView
import kotlinx.android.synthetic.main.fragment_message_list.messageRecyclerView
import kotlinx.android.synthetic.main.fragment_message_list.progressBar
import kotlinx.android.synthetic.main.fragment_message_list.rootView
import kotlinx.android.synthetic.main.fragment_message_list.submitButton
import kotlinx.android.synthetic.main.fragment_message_list.textInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.furkanakdemir.websocketsample.R
import net.furkanakdemir.websocketsample.data.Message
import net.furkanakdemir.websocketsample.data.Result
import net.furkanakdemir.websocketsample.ext.hide
import net.furkanakdemir.websocketsample.ext.hideKeyboard
import net.furkanakdemir.websocketsample.ext.show
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class MessageListFragment : DaggerFragment() {

    private lateinit var messageAdapter: MessageAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var messageViewModel: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get()

        messageViewModel.messageLiveData.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Result.Success<*> -> {
                    messageAdapter.update(it.data as List<Message>)
                    showContent()
                }
                is Result.Loading -> showLoading()
                is Result.Failure -> showEmptyText()
            }
        })

        messageViewModel.titleLiveData.observe(viewLifecycleOwner, Observer {
            requireActivity().title = it
        })

        messageViewModel.snackbarLiveData.observe(viewLifecycleOwner, Observer {
            Snackbar.make(rootView, it, Snackbar.LENGTH_LONG).show()
        })

        messageAdapter = MessageAdapter()
        messageRecyclerView.adapter = messageAdapter

        submitButton.setOnClickListener {
            val message = textInputLayout.editText?.text.toString()

            messageViewModel.sendEvent(message)

            hideKeyboard()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        messageViewModel.getMessages()
        messageViewModel.getEvents()
    }

    private fun showLoading() {
        progressBar.show()
        messageRecyclerView.hide()
        emptyTextView.hide()
    }

    private fun showContent() {
        progressBar.hide()
        messageRecyclerView.show()
        emptyTextView.hide()
    }

    private fun showEmptyText() {
        emptyTextView.text = getString(R.string.list_empty_initial)
        progressBar.hide()
        messageRecyclerView.hide()
        emptyTextView.show()
    }
}
