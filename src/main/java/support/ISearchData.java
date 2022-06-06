package support;

import java.util.List;

public interface ISearchData<T> {
    T searchFirstElement();
    List<T> searchAllElements();
    T searchMaxElement();
    T searchMinElement();
}
