package co.ff.ias.rws.utils;

public interface UseCase<I, O> {
    O process(I input);
}
