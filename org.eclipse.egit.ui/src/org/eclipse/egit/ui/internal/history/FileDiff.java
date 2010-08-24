import java.io.InputStream;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.errors.LargeObjectException;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.util.IO;
		ObjectReader reader = db.newObjectReader();
		try {
			outputEclipseDiff(d, db, reader, diffFmt);
		} finally {
			reader.release();
		}
			final ObjectReader reader, final DiffFormatter diffFmt)
			throws IOException {
		d.append("index ").append(reader.abbreviate(id1).name()). //$NON-NLS-1$
				append("..").append(reader.abbreviate(id2).name()). //$NON-NLS-1$
		final RawText a = getRawText(id1, reader);
		final RawText b = getRawText(id2, reader);
	private RawText getRawText(ObjectId id, ObjectReader reader) throws IOException {
		ObjectLoader ldr = reader.open(id);
		if (!ldr.isLarge())
			return new RawText(ldr.getCachedBytes());

		long sz = ldr.getSize();
		if (Integer.MAX_VALUE <= sz)
			throw new LargeObjectException(id);

		byte[] buf = new byte[(int) sz];
		InputStream in = ldr.openStream();
		try {
			IO.readFully(in, buf, 0, buf.length);
		} finally {
			in.close();
		}
		return new RawText(buf);