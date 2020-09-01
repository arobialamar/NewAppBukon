package arobial.ta.bukukondangan;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private String getUserID;
    private FirebaseUser user;
    private List<DataKondangan> dataKondangans = new ArrayList<>();
    public FirebaseDatabaseHelper() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        getUserID = user.getUid();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child(getUserID).child("Data Kondangan");;
    }
    public interface DataStatus{
        void DataIsLoaded(List<DataKondangan> dataKondangans, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public void readDataKondangan(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataKondangans.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    DataKondangan exampleItem = keyNode.getValue(DataKondangan.class);
                    dataKondangans.add(exampleItem);
                }
                dataStatus.DataIsLoaded(dataKondangans, keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void UpdateDataKondangan(String key, DataKondangan dataKondangan,
                                    final DataStatus dataStatus){
        mReference.child(key).setValue(dataKondangan).addOnSuccessListener
                (new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void DeleteDataKondangn(String key, final DataStatus dataStatus){
        mReference.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
