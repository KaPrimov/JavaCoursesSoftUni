package kingsGambit.contracts;

public interface AttackableGroup<T> {

    void addMember(T atackable);
    void groupTakeAttack();

}
