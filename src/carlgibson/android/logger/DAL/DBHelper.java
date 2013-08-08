package carlgibson.android.logger.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 10/07/13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "logs.db";
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "_id integer primary key autoincrement";

    private static final String UNITS_TABLE = "CREATE TABLE Units ("
            + COLUMN_ID + ", unit text NOT NULL)";

    private static final String TOPICS_TABLE = "CREATE TABLE Topics ("
            + COLUMN_ID + "topic text NOT NULL)";

    private static final String ITEMS_TABLE = new StringBuilder()
            .append("CREATE TABLE Items (")
            .append(COLUMN_ID)
            .append(", item integer NOT NULL")
            .append(", topicId integer NOT NULL")
            .append(", defaultUnit integer NOT NULL")
            .append(", defaultQuantity integer NOT NULL")
            .append(")")
            .toString();

    private static final String LOGS_TABLE = new StringBuilder()
            .append("CREATE TABLE Logs (")
            .append(COLUMN_ID)
            .append(", time integer NOT NULL")
            .append(", itemId integer NOT NULL")
            .append(", unitId integer NOT NULL")
            .append(", quantity integer NOT NULL")
            .append(", description integer NOT NULL")
            .append(")")
            .toString();

    private static final String QUANTITIES = new StringBuilder()
            .append("").toString();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //To change body of implemented methods use File | Settings | File Templates.
        database.execSQL(UNITS_TABLE);
        database.execSQL(ITEMS_TABLE);
        database.execSQL(TOPICS_TABLE);
        database.execSQL(LOGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /*
    CONSTRAINT [PK_QuantityUnits] PRIMARY KEY CLUSTERED
            (
            [id] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
            CONSTRAINT [IX_QuantityUnits] UNIQUE NONCLUSTERED
    (
            [unit] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
    ) ON [PRIMARY]


    CONSTRAINT [PK_Topics] PRIMARY KEY CLUSTERED
            (
            [id] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
            CONSTRAINT [IX_Topics] UNIQUE NONCLUSTERED
    (
            [topic] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
    ) ON [PRIMARY]

            CONSTRAINT [PK_Items] PRIMARY KEY CLUSTERED
            (
            [id] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
            CONSTRAINT [IX_Items] UNIQUE NONCLUSTERED
    (
            [item] ASC,
    [topicId] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
    ) ON [PRIMARY]


            CONSTRAINT [PK_Events_1] PRIMARY KEY CLUSTERED
            (
            [time] ASC,
    [itemId] ASC
    )WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
    ) ON [PRIMARY]

    ALTER TABLE [dbo].[Items]  WITH CHECK ADD  CONSTRAINT [FK_Items_Topics] FOREIGN KEY([topicId])
    REFERENCES [dbo].[Topics] ([id])

    ALTER TABLE [dbo].[Items] CHECK CONSTRAINT [FK_Items_Topics]

    ALTER TABLE [dbo].[Items]  WITH CHECK ADD  CONSTRAINT [FK_Items_Units] FOREIGN KEY([defaultUnit])
    REFERENCES [dbo].[Units] ([id])

    ALTER TABLE [dbo].[Items] CHECK CONSTRAINT [FK_Items_Units]



    ALTER TABLE [dbo].[Logs]  WITH CHECK ADD  CONSTRAINT [FK_Events_Items] FOREIGN KEY([itemId])
    REFERENCES [dbo].[Items] ([id])

    ALTER TABLE [dbo].[Logs] CHECK CONSTRAINT [FK_Events_Items]

    ALTER TABLE [dbo].[Logs]  WITH CHECK ADD  CONSTRAINT [FK_Events_Units] FOREIGN KEY([unitId])
    REFERENCES [dbo].[Units] ([id])

    ALTER TABLE [dbo].[Logs] CHECK CONSTRAINT [FK_Events_Units]
    */
}
