package co.ff.ias.utils;

public interface UseCase<I, O> {
    O process(I input);
}
