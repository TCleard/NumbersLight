package fr.tcleard.numberslight.scene.main.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

import fr.tcleard.numberslight.R;
import fr.tcleard.numberslight.core.model.Item;
import fr.tcleard.numberslight.scene.main.list.adapter.ItemAdapter;
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel;
import fr.tcleard.numberslight.ui.viewController.AFragment;

public class ListFragment extends AFragment<ListPresenter> implements ListPresenter.ListView {

    private SwipeRefreshLayout listRefresh;
    private RecyclerView list;

    @Inject
    protected ItemAdapter adapter;

    private Listener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerListComponent.builder()
                .appComponent(getAppComponent())
                .listModule(new ListModule())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listRefresh = view.findViewById(R.id.listRefresh);
        list = view.findViewById(R.id.listContent);

        listRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getItems();
            }
        });

        list.setLayoutManager(new LinearLayoutManager(requireContext()));
        list.setAdapter(adapter);

        presenter.attach(this);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * ListView
     **/

    @Override
    public void showLoading(boolean loading) {
        listRefresh.setRefreshing(loading);
        if (loading) {
            adapter.removeAll(true);
        }
    }

    @Override
    public void showItems(@NotNull List<ItemViewModel> items) {
        adapter.setItems(items.toArray(new ItemViewModel[items.size()]), false);
    }

    @Override
    public void updateItem(@NotNull ItemViewModel item) {
        adapter.update(item);
    }

    @Override
    public void onItemClicked(@NotNull Item item) {
        if (listener != null) {
            listener.onItemClicked(item);
        }
    }

    /**
     * Listeners
     **/

    public interface Listener {

        void onItemClicked(Item item);

    }

}
