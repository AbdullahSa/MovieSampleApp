package com.abdullah.moviereviewapp.feature.presentation.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.databinding.DialogSelectableCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectableBottomSheetDialog(
    private val list: List<SelectableListItem> = arrayListOf(),
    private val onItemClicked: (SelectableListItem) -> Unit
) :
    BottomSheetDialogFragment() {

    companion object {

        fun newInstance(
            list: List<SelectableListItem>,
            onItemClicked: (SelectableListItem) -> Unit
        ): SelectableBottomSheetDialog {
            val args = Bundle()
            return SelectableBottomSheetDialog(list, onItemClicked).apply {
                arguments = args
            }
        }
    }

    private lateinit var binding: DialogSelectableCategoryBinding

    private val listAdapter by lazy {
        SelectableListAdapter {
            onItemClicked.invoke(it)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_selectable_category,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivDrawer.setOnClickListener {
                dismiss()
            }
            rvSelectableList.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ContextCompat.getDrawable(context, R.drawable.shape_divider_section)?.let {
                    itemDecoration.setDrawable(it)
                }
                addItemDecoration(itemDecoration)
                adapter = listAdapter
            }
        }
        listAdapter.submitList(list)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), R.style.MyTransparentBottomSheetDialogTheme).apply {
            window?.attributes?.windowAnimations = R.style.DialogAnimation
        }

}