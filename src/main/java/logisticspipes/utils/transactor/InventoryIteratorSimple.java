package logisticspipes.utils.transactor;

import java.util.Iterator;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import logisticspipes.utils.InventoryHelper;

class InventoryIteratorSimple implements Iterable<IInvSlot> {

    private final IInventory inv;

    InventoryIteratorSimple(IInventory inv) {
        this.inv = InventoryHelper.getInventory(inv);
    }

    @Override
    public Iterator<IInvSlot> iterator() {
        return new Iterator<IInvSlot>() {

            int slot = 0;

            @Override
            public boolean hasNext() {
                return slot < inv.getSizeInventory();
            }

            @Override
            public IInvSlot next() {
                return new InvSlot(slot++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not supported.");
            }
        };
    }

    private class InvSlot implements IInvSlot {

        private final int slot;

        public InvSlot(int slot) {
            this.slot = slot;
        }

        @Override
        public ItemStack getStackInSlot() {
            return inv.getStackInSlot(slot);
        }

        @Override
        public void setStackInSlot(ItemStack stack) {
            inv.setInventorySlotContents(slot, stack);
        }

        @Override
        public boolean canPutStackInSlot(ItemStack stack) {
            return inv.isItemValidForSlot(slot, stack);
        }
    }
}
